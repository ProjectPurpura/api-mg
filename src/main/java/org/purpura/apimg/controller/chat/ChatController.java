package org.purpura.apimg.controller.chat;

import org.purpura.apimg.controller.chat.oas.ChatContract;
import org.purpura.apimg.dto.mapper.conversa.MessageMapper;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageBatchRequestDTO;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.service.ChatService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    public ChatController(ChatService service, MessageMapper messageMapper) {
        this.service = service;
        this.messageMapper = messageMapper;
    }

    @Override
    public ResponseEntity<List<ChatResponseDTO>> getAllByParticipantId(String id) {
        return ResponseEntity.ok(service.findAllByParticipantId(id));
    }

    @Override
    public ResponseEntity<ChatResponseDTO> getChat(String chatId) {
        ChatModel model = service.findById(chatId);
        ChatResponseDTO dto = new ChatResponseDTO();
        BeanUtils.copyProperties(model, dto);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ChatResponseDTO> createChat(CreateChatRequestDTO createChatRequestDTO) {
        ChatModel model =  service.createChat(createChatRequestDTO);
        return ResponseEntity.ok(ChatResponseDTO.builder()
                .id(model.getId())
                .participants(model.getParticipants())
                .build());
    }

    @Override
    public ResponseEntity<Void> deleteChat(String chatId) {
        service.deleteChat(chatId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByChatId(String chatId) {

        List<MessageResponseDTO> responseDTOS = messageMapper
                .toResponseList(service.findMessagesByChatId(chatId));
        return ResponseEntity.ok(responseDTOS);
    }

    @Override
    public void processMessage(MessageRequestDTO dto) {
        service.sendMessage(dto);
    }

    @Override
    @MessageMapping("/chat.markRead")
    public void markMessagesRead(@Payload MessageBatchRequestDTO requestDTO) {
        service.markMessagesRead(requestDTO.getMessageIds());
    }
}
