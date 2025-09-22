package org.purpura.apimg.dto.conversa.chat;

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
@Schema(description = "DTO para criação de um novo chat, contendo a lista de IDs dos participantes.")
public class CreateChatRequestDTO {
    @Schema(description = "Lista de IDs dos participantes que serão adicionados ao novo chat")
    private List<String> participants;
}
