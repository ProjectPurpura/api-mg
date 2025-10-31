package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.purpura.apimg.validation.residuo.DownturnUnique;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="ResiduoDownturnRequestDTO", description = "Representa a requisição de baixa de vários resíduos")
public class ResiduoDownturnRequestDTO {
    @Schema(description = "Requsição de baixa de um resíduo específico")
    @Size(min = 1, message = "A lista de baixas deve ter pelo menos um item")
    @DownturnUnique
    private List<EstoqueDownturn> estoqueDownturns;
}
