package org.purpura.apimg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.purpura.apimg.dto.conversa.CreateChatRequestDTO;
import org.purpura.apimg.dto.conversa.MessageRequestDTO;
import org.purpura.apimg.exception.conversa.ChatNotFoundException;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.model.conversa.MessageModel;
import org.purpura.apimg.repository.ChatRepository;
import org.purpura.apimg.repository.MessageRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public ChatService(
            ChatRepository chatRepository,
            MessageRepository messageRepository,
            StringRedisTemplate redisTemplate,
            ObjectMapper objectMapper
    ) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public ChatModel findById(String chatId) {
        return chatRepository
                .findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
    }


    @Transactional
    public ChatModel createChat(CreateChatRequestDTO createChatRequestDTO) {
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



    public MessageModel sendMessage(MessageRequestDTO messageRequestDTO) {
        String chatId = messageRequestDTO.getChatId();
        ChatModel chat = findById(chatId); // ensures exists

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

        return saved;
    }

    private void validateSender(ChatModel chat, String senderId) {
        if (!chat.getParticipants().contains(senderId)) {
            throw new IllegalArgumentException(String.format("Participant %s is not part of chat %s", senderId, chat.getId()));
        }
    }

}
