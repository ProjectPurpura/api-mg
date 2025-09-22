package org.purpura.apimg.dto.alive;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa a resposta de um ping de API.")
public record AliveResponseDTO(
        @Schema(description = "Status da API")
        String status,

        @Schema(description = "Mensagem de resposta de teste, sempre será 'banana'")
        String message
) {}