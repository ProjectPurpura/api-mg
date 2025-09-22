package org.purpura.apimg.dto.empresa.base;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO para requisições de criação/atualização de empresa.")
public class EmpresaRequestDTO {

    @Schema(description = "CNPJ da empresa. Deve ser válido.", example = "12345678000195")
    @NotNull(message = "O CNPJ da empresa precisa ser informado")
    @NotBlank(message = "O CNPJ da empresa precisa ser informado")
    @CNPJ(message = "O CNPJ da empresa precisa ser válido")
    private String cnpj;

    @Schema(description = "Nome da empresa.", example = "Empresa Exemplo")
    @NotNull(message = "O nome da empresa precisa ser informado")
    @NotBlank(message = "O nome da empresa precisa ser informado")
    @Length(min = 3, max = 100, message = "O nome da empresa deve ter entre 3 e 100 caracteres")
    private String nome;

    @Schema(description = "E-mail da empresa.", example = "contato@empresa.com")
    @NotNull(message = "O e-mail da empresa precisa ser informado")
    @NotBlank(message = "O e-mail da empresa precisa ser informado")
    @Email(message = "O e-mail da empresa precisa ser um e-mail válido")
    private String email;

    @Schema(description = "Telefone da empresa, apenas números com DDD.", example = "11999999999")
    @NotNull(message = "O telefone da empresa precisa ser informado")
    @NotBlank(message = "O telefone da empresa precisa ser informado")
    @Pattern(regexp = "^\\d{2}(?:9\\d{8}|\\d{8})$", message = "O telefone da empresa deve seguir formato somente números com DDD")
    private String telefone;

    @Schema(description = "URL da foto da empresa.", example = "https://empresa.com/foto.png")
    @URL
    private String urlFoto;
}
