package org.purpura.apimg.controller;

import org.purpura.apimg.dto.conversa.ChatResponseDTO;
import org.purpura.apimg.dto.conversa.CreateChatRequestDTO;
import org.purpura.apimg.dto.conversa.MessageRequestDTO;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@Validated
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<ChatResponseDTO> createChat(@RequestBody CreateChatRequestDTO createChatRequestDTO) {
        ChatModel model =  service.createChat(createChatRequestDTO);
        return ResponseEntity.ok(ChatResponseDTO.builder()
                .chatId(model.getId())
                .participants(model.getParticipants())
                .build());
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable String chatId) {
        service.deleteChat(chatId);
        return ResponseEntity.ok().build();
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageRequestDTO dto) {
        // chatId comes from payload
        service.sendMessage(dto);
    }
}
