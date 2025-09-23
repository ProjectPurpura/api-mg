package org.purpura.apimg.controller.chat;

import org.purpura.apimg.dto.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.conversa.mensagem.MessageResponseDTO;
import org.purpura.apimg.model.conversa.ChatModel;
import org.purpura.apimg.model.conversa.MessageModel;
import org.purpura.apimg.service.ChatService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Validated
public class ChatController implements ChatContract {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ChatResponseDTO>> getAllByParticipantId(String id) {
        return ResponseEntity.ok(service.findAllByParticipantId(id).stream()
                .map(e -> {
                    ChatResponseDTO dto = new ChatResponseDTO();
                    BeanUtils.copyProperties(e, dto);
                    return dto;
                }).toList());
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
        List<MessageModel> messageModels = service.findMessagesByChatId(chatId);
        List<MessageResponseDTO> responseDTOS = messageModels.stream()
                .map(x -> {
                    MessageResponseDTO dto = new MessageResponseDTO();
                    BeanUtils.copyProperties(x, dto);
                    return dto;
                }).toList();

        return ResponseEntity.ok(responseDTOS);
    }

    @Override
    public void processMessage(MessageRequestDTO dto) {
        service.sendMessage(dto);
    }
}
