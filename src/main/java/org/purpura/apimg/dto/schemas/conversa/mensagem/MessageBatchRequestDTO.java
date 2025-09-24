package org.purpura.apimg.dto.schemas.conversa.mensagem;

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
@Schema(description = "DTO usado para realizar uma operação em N mensagens")
public class MessageBatchRequestDTO {

    @Schema(description = "IDs das mensagens a serem operadas", example = "[\"i5d6m7e8n9s8a4g2em1\", \"i2d4m5e6n6s7a8g9e9m41a\"]")
    private List<String> messageIds;
}

