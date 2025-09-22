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
@Schema(description = "DTO para envio de mensagem em um chat, incluindo o ID do chat, ID do remetente e conteúdo da mensagem.")
public class MessageRequestDTO {
    @Schema(description = "ID do chat para o qual a mensagem será enviada")
    private String chatId;
    @Schema(description = "ID do usuário que está enviando a mensagem")
    private String senderId;
    @Schema(description = "Conteúdo da mensagem em markdown")
    private String content;
}
