package org.purpura.apimg.dto.conversa.mensagem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {
    private String messageId;
    private String senderId;
    private String corpo;
    private Long timestamp;
    private boolean read;
}
