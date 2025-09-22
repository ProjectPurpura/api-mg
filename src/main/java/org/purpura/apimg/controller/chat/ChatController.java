package org.purpura.apimg.controller.chat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.purpura.apimg.dto.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.conversa.mensagem.MessageRequestDTO;
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
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @Operation(
        summary = "Buscar todos os chats por participante",
        description = "Retorna uma lista de chats onde o participante informado está presente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de chats retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<ChatResponseDTO>> getAllByParticipantId(
        @Parameter(description = "ID do participante", required = true)
        @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findAllByParticipantId(id).stream()
                .map(e -> {
                    ChatResponseDTO dto = new ChatResponseDTO();
                    BeanUtils.copyProperties(e, dto);
                    return dto;
                }).toList())
                ;
    }

    @Operation(
        summary = "Criar novo chat",
        description = "Cria um novo chat com os participantes informados."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Chat criado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/new")
    public ResponseEntity<ChatResponseDTO> createChat(
        @RequestBody(
            description = "DTO contendo os participantes para o novo chat",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateChatRequestDTO.class))
        )
        CreateChatRequestDTO createChatRequestDTO
    ) {
        ChatModel model =  service.createChat(createChatRequestDTO);
        return ResponseEntity.ok(ChatResponseDTO.builder()
                .chatId(model.getId())
                .participants(model.getParticipants())
                .build());
    }

    @Operation(
        summary = "Excluir chat",
        description = "Exclui o chat com o ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Chat excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Chat não encontrado")
    })
    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(
        @Parameter(description = "ID do chat a ser excluído", required = true)
        @PathVariable String chatId
    ) {
        service.deleteChat(chatId);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Enviar mensagem para o chat (WebSocket)",
        description = "Processa e envia uma mensagem para o chat especificado via WebSocket."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mensagem enviada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Payload da mensagem inválido")
    })
    @MessageMapping("/chat")
    public void processMessage(
        @Payload
        @Parameter(description = "DTO contendo os dados da mensagem", required = true)
        MessageRequestDTO dto
    ) {
        // chatId comes from payload
        service.sendMessage(dto);
    }
}
