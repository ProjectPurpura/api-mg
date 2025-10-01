package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.purpura.apimg.model.empresa.TipoUnidade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para requisições de criação/atualização de resíduo da empresa.")
public class ResiduoRequestDTO {
    @Schema(description = "Nome do resíduo.", example = "Papelão")
    @NotNull(message = "O nome do resíduo precisa ser informado e não nula")
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome do resíduo deve ter pelo menos dois caracteres não brancos")
    private String nome;

    @Schema(description = "Descrição do resíduo.", example = "Resíduo reciclável de papelão.")
    private String descricao;

    @Schema(description = "Peso do resíduo.", example = "10.50")
    @NotNull(message = "O peso do resíduo precisa ser informado e não nulo")
    @DecimalMin(value = "0.001", message = "O peso do resíduo deve ser maior que 0.001")
    private Double peso;

    @Schema(description = "Preço do resíduo.", example = "10.50")
    @NotNull(message = "O preço do resíduo precisa ser informado e não nulo")
    @DecimalMin(value = "0.001", message = "O preço do resíduo deve ser maior que 0.001")
    private Double preco;

    @Schema(description = "Quantidade em estoque.", example = "100")
    @NotNull(message = "A quantidade em estoque do produto deve ser informada e não nula")
    @PositiveOrZero(message = "A quantidade em estoque do produto deve ser positiva ou zero")
    private Long estoque;

    @Schema(description = "Unidade de medida do produto.", example = "KG")
    @NotNull(message = "A Unidade de medida do produto deve ser informada e não nula")
    private TipoUnidade tipoUnidade;

    @Schema(description = "URL da foto do resíduo.", example = "https://dicaparacasa.com.br/wp-content/uploads/2023/04/Quanto-Custa-1-kg-de-Papelao.png")
    @URL(message = "A URL da foto do resíduo precisa ser uma URL válida")
    private String urlFoto;
}
