package org.purpura.apimg.dto.empresa.residuo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.purpura.apimg.model.empresa.Unidade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResiduoRequestDTO {
    @NotNull(message = "O nome do resíduo precisa ser informado e não nula")
    @Pattern(regexp = "\\S{5,50}", message = "O nome do resíduo deve ter entre 5 e 50 caracteres alfanuméricos latinos ou especiais")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço do resíduo precisa ser informado e não nulo")
    @DecimalMin(value = "0.01", message = "")
    private Double preco;

    @URL(message = "A URL da foto do resíduo precisa ser uma URL válida")
    private String urlFoto;

    @NotNull(message = "A quantidade em estoque do produto deve ser informada e não nula")
    @PositiveOrZero(message = "A quantidade em estoque do produto deve ser positiva ou zero")
    private Long estoque;

    @NotNull(message = "A unidade de medida do produto deve ser informada e não nula")
    private Unidade unidade;
}
