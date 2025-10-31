package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="ResiduoDownturnResponseDTO", description = "Representa a requisição de baixa de vários resíduos")
public class ResiduoDownturnResponseDTO {
    @Schema(description = "Lista de baixas de estoque de resíduos")
    @Size(min = 1, message = "A lista de baixas deve ter pelo menos um item")
    private List<EstoqueDownturn> estoqueDownturns;
}
