package org.purpura.apimg.dto.schemas.conversa.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, message = "O chat deve ter pelo menos dois participantes")
    private List<@NotNull(message = "O id do participante não pode ser nulo") String> participants;
}
