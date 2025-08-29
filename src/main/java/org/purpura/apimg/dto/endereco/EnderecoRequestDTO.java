package org.purpura.apimg.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "O CEP do endereço precisa ser informado")
    @NotBlank(message = "O CEP do endereço precisa ser informado")
    private String cep;

    @NotBlank(message = "O complemento do endereço não pode ser vazio")
    private String complemento;

    @PositiveOrZero(message = "O número do endereço deve ser positivo ou zero")
    private Integer numero;
}
