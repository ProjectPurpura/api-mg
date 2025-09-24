package org.purpura.apimg.controller.chat.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docs/websocket")
public class WebSocketDocsController {

    @Operation(
        summary = "Documentação dos endpoints WebSocket (STOMP)",
        description = "Lista os endpoints WebSocket disponíveis, destinos STOMP, payloads de exemplo e descrições."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Documentação retornada com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> getWebSocketDocs() {
        List<Map<String, Object>> docs = List.of(
            Map.of(
                "name", "Enviar mensagem para o chat",
                "stompDestination", "/app/chat",
                "description", "Envia uma mensagem para o chat especificado. Mensagem será broadcast para /topic/chat.{chatId}.",
                "payloadExample", Map.of(
                    "chatId", "string",
                    "senderId", "string",
                    "content", "string"
                )
            ),
            Map.of(
                "name", "Marcar mensagens como lidas",
                "stompDestination", "/app/chat.markRead",
                "description", "Marca as mensagens como lidas. Não há resposta direta.",
                "payloadExample", Map.of(
                    "messageIds", List.of("id1", "id2")
                )
            )
        );
        return ResponseEntity.ok(docs);
    }
}

