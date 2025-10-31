package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="Downturn", description = "Representa a requisição de baixa de um resíduo específico")
public class Downturn {
    @Schema(name = "ID do resíduo")
    @NotNull(
            message = "O id do resíduo relativo a essa baixa deve ser informado e não nulo"
    )
    private String idResiduo;

    @Schema(name = "Quantidade da baixa, valores negativos aumentam o estoque")
    @NotNull(
            message = "A quantidade de baixa deve ser informada e não nula"
    )
    private Double quantidade;
}
