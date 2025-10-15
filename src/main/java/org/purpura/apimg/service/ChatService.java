package org.purpura.apimg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.exception.conversa.ChatAlreadyExistsException;
import org.purpura.apimg.exception.conversa.ChatNotFoundException;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.model.conversa.MessageModel;
import org.purpura.apimg.repository.ChatRepository;
import org.purpura.apimg.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public ChatModel findById(String chatId) {
        return chatRepository
                .findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
    }

    public ChatResponseDTO getChatResponse(ChatModel chat, String userId) {
        ChatResponseDTO dto = new ChatResponseDTO();
        BeanUtils.copyProperties(chat, dto);
        long unreadCount = messageRepository.countByChatIdAndSenderIdNotAndReadFalse(chat.getId(), userId);
        dto.setUnreadCount((int) unreadCount);

        String lastMessageId = chat.getLastMessageId();
        if (lastMessageId != null) {
            Optional<MessageModel> lastMessage = messageRepository.findById(chat.getLastMessageId());
            lastMessage.ifPresent(messageModel -> dto.setLastMessagePreview(messageModel.getContent()));
        }
        return dto;
    }

    public List<ChatResponseDTO> findAllByParticipantId(String userId) {
        List<ChatModel> chatModels = chatRepository.findByParticipantsContains(userId);
        return chatModels.stream()
                .map(e -> getChatResponse(e, userId))
                .toList();
    }

    @Transactional
    public ChatModel createChat(CreateChatRequestDTO createChatRequestDTO) {
        List<ChatModel> existingChats = chatRepository
                .findByParticipantsContainingAll(createChatRequestDTO.getParticipants().toArray(new String[]{}));
        if (!existingChats.isEmpty()) {
            return existingChats.getFirst();
        }
        ChatModel model = ChatModel.builder()
                .participants(createChatRequestDTO.getParticipants())
                .build();
        return chatRepository.save(model);
    }

    @Transactional
    public void deleteChat(String chatId) {
        ChatModel chatModel = findById(chatId);
        chatRepository.delete(chatModel);
    }



    public void sendMessage(MessageRequestDTO messageRequestDTO) {
        String chatId = messageRequestDTO.getChatId();
        ChatModel chat = findById(chatId);

        validateSender(chat, messageRequestDTO.getSenderId());

        MessageModel messageModel = MessageModel.builder()
                .chatId(chatId)
                .senderId(messageRequestDTO.getSenderId())
                .content(messageRequestDTO.getContent())
                .build();

        MessageModel saved = messageRepository.save(messageModel);

        // update chat metadata
        chat.setLastMessageId(saved.getId());
        chat.setUpdatedTimestamp(System.currentTimeMillis());
        chatRepository.save(chat);

        try {
            String payload = objectMapper.writeValueAsString(messageModel);
            redisTemplate.convertAndSend("chat." + chatId, payload);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not serialize message", e);
        }

    }

    private void validateSender(ChatModel chat, String senderId) {
        if (!chat.getParticipants().contains(senderId)) {
            throw new IllegalArgumentException(String.format("Participant %s is not part of chat %s", senderId, chat.getId()));
        }
    }



    public List<MessageModel> findMessagesByChatId(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampDesc(chatId);
    }

    public void markMessagesRead(List<String> messageIds) {
        List<MessageModel> messages = messageRepository.findAllById(messageIds);
        for (MessageModel message : messages) {
            message.setRead(true);
        }
        messageRepository.saveAll(messages);

        Set<String> chatIds = new HashSet<>();
        for (MessageModel message : messages) {
            if (message.getChatId() != null) {
                chatIds.add(message.getChatId());
            }
        }
        List<ChatModel> chats = chatRepository.findAllById(chatIds);
        long now = System.currentTimeMillis();
        for (ChatModel chat : chats) {
            chat.setUpdatedTimestamp(now);
        }
        chatRepository.saveAll(chats);
    }
}
