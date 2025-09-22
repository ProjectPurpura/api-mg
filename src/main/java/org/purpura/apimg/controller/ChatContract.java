package org.purpura.apimg.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OpenAPI-documented contract for chat operations.
 */
public interface ChatContract {

    @Operation(
        summary = "Get all chats by participant ID",
        description = "Returns a list of chats where the given participant is involved."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of chats returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class)))
    })
    @GetMapping("/chat/{id}")
    ResponseEntity<List<ChatResponseDTO>> getAllByParticipantId(
        @Parameter(description = "ID of the participant", required = true)
        @PathVariable String id
    );

    @Operation(
        summary = "Create a new chat",
        description = "Creates a new chat with the specified participants."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Chat created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping("/chat/new")
    ResponseEntity<ChatResponseDTO> createChat(
        @RequestBody(
            description = "DTO containing participants for the new chat",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateChatRequestDTO.class))
        )
        CreateChatRequestDTO createChatRequestDTO
    );

    @Operation(
        summary = "Delete a chat",
        description = "Deletes the chat with the specified chat ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Chat deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Chat not found")
    })
    @DeleteMapping("/chat/{chatId}")
    ResponseEntity<Void> deleteChat(
        @Parameter(description = "ID of the chat to delete", required = true)
        @PathVariable String chatId
    );

    @Operation(
        summary = "Send a message to a chat (WebSocket)",
        description = "Processes and sends a message to the specified chat via WebSocket."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Message sent successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid message payload")
    })
    @MessageMapping("/chat")
    void processMessage(
        @Payload
        @Parameter(description = "DTO containing message data", required = true)
        MessageRequestDTO dto
    );
}

