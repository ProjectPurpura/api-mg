package org.purpura.apimg.dto.empresa.base;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Validated
public class EmpresaRequestDTO {

    @NotNull(message = "O CNPJ da empresa precisa ser informado")
    @NotBlank(message = "O CNPJ da empresa precisa ser informado")
    @CNPJ(message = "O CNPJ da empresa precisa ser válido")
    private String cnpj;

    @NotNull(message = "O nome da empresa precisa ser informado")
    @NotBlank(message = "O nome da empresa precisa ser informado")
    @Length(min = 3, max = 100, message = "O nome da empresa deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O e-mail da empresa precisa ser informado")
    @NotBlank(message = "O e-mail da empresa precisa ser informado")
    @Email(message = "O e-mail da empresa precisa ser um e-mail válido")
    private String email;

    @NotNull(message = "O telefone da empresa precisa ser informado")
    @NotBlank(message = "O telefone da empresa precisa ser informado")
    @Pattern(regexp = "^\\d{2}(?:9\\d{8}|\\d{8})$", message = "O telefone da empresa deve seguir formato somente números com DDD")
    private String telefone;

    @URL
    private String urlFoto;
}
