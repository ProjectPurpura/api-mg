package org.purpura.apimg.controller.chat.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageBatchRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ChatContract {

    @Operation(
        summary = "Buscar todos os chats por participante",
        description = "Retorna uma lista de chats onde o participante informado está presente.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de chats retornada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class)))
        }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    List<ChatResponseDTO> getAllByParticipantId(
        @Parameter(description = "ID do participante", required = true)
        @PathVariable String id
    );

    @Operation(
            summary = "Buscar os dados de um chat por ID",
            description = "Retorna os metadados de um chat.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de chats retornada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Chat não encontrado")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{chatId}")
    ChatResponseDTO getChat(@PathVariable String chatId);

    @Operation(
        summary = "Criar novo chat",
        description = "Cria um novo chat com os participantes informados.",
        responses = {
                @ApiResponse(responseCode = "201", description = "Chat criado com sucesso",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                @ApiResponse(responseCode = "409", description = "Chat já existe para os participantes dados")
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    ChatResponseDTO createChat(
        @RequestBody(
            description = "DTO contendo os participantes para o novo chat",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateChatRequestDTO.class))
        )
        CreateChatRequestDTO createChatRequestDTO
    );

    @Operation(
        summary = "Excluir chat",
        description = "Exclui o chat com o ID informado.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Chat excluído com sucesso"),
                @ApiResponse(responseCode = "404", description = "Chat não encontrado")
        }
    )
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{chatId}")
    void deleteChat(
        @Parameter(description = "ID do chat a ser excluído", required = true)
        @PathVariable String chatId
    );


    @Operation(
            summary = "Buscar mensagens de um chat",
            description = "Retorna uma lista de mensagens de um chat com o ID informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de mensagens retornada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Chat não encontrado")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{chatId}/messages")
    List<MessageResponseDTO> getMessagesByChatId(@PathVariable String chatId);

    @Operation(
            summary = "Enviar mensagem para o chat (WebSocket)",
            description = "Processa e envia uma mensagem para o chat especificado via WebSocket.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mensagem enviada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Payload da mensagem inválido")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @MessageMapping("/chat")
    void processMessage(
            @Payload
            @Parameter(description = "DTO contendo os dados da mensagem", required = true)
            MessageRequestDTO dto
    );


    @Operation(
            summary = "Marcar mensagens como lidas (WebSocket)",
            description = "Marca as mensagens de um chat como lidas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mensagens marcadas como lidas com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Payload inválido")
            }
    )
    @MessageMapping("/chat.markRead")
    void markMessagesRead(@Payload MessageBatchRequestDTO requestDTO);
}
