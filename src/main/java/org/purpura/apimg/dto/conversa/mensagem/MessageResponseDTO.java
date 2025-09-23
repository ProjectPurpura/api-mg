package org.purpura.apimg.dto.conversa.mensagem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa a resposta de uma mensagem, incluindo o ID da mensagem, ID do remetente, corpo, data/hora e status de leitura.")
public class MessageResponseDTO {
    @Schema(description = "Identificador único da mensagem")
    private String id;
    @Schema(description = "ID do usuário que enviou a mensagem")
    private String senderId;
    @Schema(description = "Corpo/conteúdo da mensagem em markdown")
    private String content;
    @Schema(description = "Indica se a mensagem foi lida")
    private boolean read;
    @Schema(description = "Data/hora em que a mensagem foi enviada (timestamp)")
    private Long timestamp;

}
