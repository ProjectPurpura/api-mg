package org.purpura.apimg.dto.empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
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
    private String cCdEmpresa;

    @NotNull(message = "O nome da empresa precisa ser informado")
    @NotBlank(message = "O nome da empresa precisa ser informado")
    @Length(min = 3, max = 100, message = "O nome da empresa deve ter entre 3 e 100 caracteres")
    private String cNmEmpresa;

    @NotNull(message = "O e-mail da empresa precisa ser informado")
    @NotBlank(message = "O e-mail da empresa precisa ser informado")
    @Email(message = "O e-mail da empresa precisa ser um e-mail válido")
    private String cEmail;

    @NotNull(message = "O telefone da empresa precisa ser informado")
    @NotBlank(message = "O e-mail da empresa precisa ser informado")
    private String cTelefone;
}
