package org.purpura.apimg.controller.chat;

import jakarta.validation.Valid;
import org.purpura.apimg.controller.chat.oas.ChatContract;
import org.purpura.apimg.dto.mapper.conversa.ChatMapper;
import org.purpura.apimg.dto.mapper.conversa.MessageMapper;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageBatchRequestDTO;
import org.purpura.apimg.service.ChatService;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Validated
public class ChatController implements ChatContract {

    private final ChatService service;
    private final MessageMapper messageMapper;
    private final ChatMapper chatMapper;

    public ChatController(ChatService service, MessageMapper messageMapper, ChatMapper chatMapper) {
        this.service = service;
        this.messageMapper = messageMapper;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<ChatResponseDTO> getAllByParticipantId(String id) {
        return service.findAllByParticipantId(id);
    }

    @Override
    public ChatResponseDTO getChat(String chatId) {
        return chatMapper
                .toResponse(service.findById(chatId));
    }

    @Override
    public ChatResponseDTO createChat(CreateChatRequestDTO createChatRequestDTO) {
        return chatMapper
                .toResponse(service.createChat(createChatRequestDTO));
    }

    @Override
    public void deleteChat(String chatId) {
        service.deleteChat(chatId);
    }

    @Override
    public List<MessageResponseDTO> getMessagesByChatId(String chatId) {
        return messageMapper
                .toResponseList(service.findMessagesByChatId(chatId));
    }

    @Override
    public void processMessage(@Payload MessageRequestDTO dto) {
        service.sendMessage(dto);
    }

    @Override
    public void markMessagesRead(@Payload MessageBatchRequestDTO requestDTO) {
        service.markMessagesRead(requestDTO.getMessageIds());
    }
}
