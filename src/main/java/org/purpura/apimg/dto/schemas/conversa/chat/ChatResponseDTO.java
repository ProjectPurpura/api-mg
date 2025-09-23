package org.purpura.apimg.dto.schemas.conversa.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa a resposta de um chat, incluindo o ID do chat, participantes, prévia da última mensagem, data da última atualização e quantidade de mensagens não lidas.")
public class ChatResponseDTO {
    @Schema(description = "Identificador único do chat")
    private String id;
    @Schema(description = "Lista de IDs dos participantes do chat")
    private List<String> participants;
    @Schema(description = "Prévia da última mensagem do chat, ou nulo se for um chat novo")
    @Builder.Default
    private String lastMessagePreview = null;  // opcional, pode ser nulo se for novo chat
    @Schema(description = "Data/hora da última atualização do chat (timestamp)")
    @Builder.Default
    private Long lastUpdated = System.currentTimeMillis();
    @Schema(description = "Quantidade de mensagens não lidas para o usuário")
    @Builder.Default
    private int unreadCount = 0;            // calculado por usuário
}