package org.purpura.apimg.dto.schemas.empresa.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisições de criação/atualização de endereço de empresa.")
public class EnderecoRequestDTO {
    @Schema(description = "Apelido do endereço.", example = "Matriz")
    @NotNull(message = "O apelido do endereço precisa ser informado")
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome do endereço deve conter pelo menos dois caracteres")
    private String nome;

    @Schema(description = "CEP do endereço, apenas números.", example = "01001000")
    @NotNull(message = "O CEP do endereço precisa ser informado")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve ser composto exclusivamente por 8 números")
    private String cep;

    @Schema(description = "Complemento do endereço.", example = "Sala 101")
    private String complemento;

    @Schema(description = "Número do endereço.", example = "100")
    @PositiveOrZero(message = "O número do endereço deve ser positivo ou zero")
    private Integer numero;
}
