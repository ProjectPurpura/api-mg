package org.purpura.apimg.dto.empresa.endereco;

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
public class EnderecoRequestDTO {
    @NotNull(message = "O apelido do endereço precisa ser informado")
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome do endereço deve conter pelo menos dois caracteres")
    private String nome;

    @NotNull(message = "O CEP do endereço precisa ser informado")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve ser composto exclusivamente por 8 números")
    private String cep;

    @NotBlank(message = "O complemento do endereço não pode ser vazio")
    private String complemento;

    @PositiveOrZero(message = "O número do endereço deve ser positivo ou zero")
    private Integer numero;
}
