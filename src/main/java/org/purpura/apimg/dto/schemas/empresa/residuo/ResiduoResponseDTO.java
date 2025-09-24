package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.purpura.apimg.model.empresa.Unidade;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resíduo da empresa")
public class ResiduoResponseDTO implements java.io.Serializable {
    @Schema(description = "Identificador único do resíduo")
    private String id;
    @Schema(description = "Nome do resíduo")
    private String nome;
    @Schema(description = "Descrição detalhada do resíduo")
    private String descricao;
    @Schema(description = "Preço do resíduo")
    private Double preco;
    @Schema(description = "Unidade de medida do resíduo")
    private Unidade unidade;
    @Schema(description = "Quantidade em estoque do resíduo")
    private Long estoque;
    @Schema(description = "URL da foto do resíduo")
    private String urlFoto;
}
