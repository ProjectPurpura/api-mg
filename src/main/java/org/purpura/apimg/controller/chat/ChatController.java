package org.purpura.apimg.controller.chat;

import lombok.RequiredArgsConstructor;
import org.purpura.apimg.controller.chat.oas.ChatContract;
import org.purpura.apimg.dto.mapper.conversa.ChatMapper;
import org.purpura.apimg.dto.mapper.conversa.MessageMapper;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageBatchRequestDTO;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController implements ChatContract {

    private final ChatService service;
    private final MessageMapper messageMapper;
    private final ChatMapper chatMapper;

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
    public ResponseEntity<ChatResponseDTO> createChat(CreateChatRequestDTO createChatRequestDTO) {
        ChatModel existingChat = service.getExistingChatByParticipants(createChatRequestDTO.getParticipants());
        if (existingChat != null) {
            return ResponseEntity.ok(chatMapper.toResponse(existingChat));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chatMapper
                    .toResponse(service.saveChat(createChatRequestDTO))
                );
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
