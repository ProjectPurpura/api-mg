package org.purpura.apimg.dto.conversa.mensagem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO que representa a resposta de uma mensagem, incluindo o ID da mensagem, ID do remetente, corpo, data/hora e status de leitura.")
public class MessageResponseDTO {
    @Schema(description = "Identificador único da mensagem")
    private String messageId;
    @Schema(description = "ID do usuário que enviou a mensagem")
    private String senderId;
    @Schema(description = "Corpo/conteúdo da mensagem")
    private String corpo;
    @Schema(description = "Data/hora em que a mensagem foi enviada (timestamp)")
    private Long timestamp;
    @Schema(description = "Indica se a mensagem foi lida")
    private boolean read;
}
