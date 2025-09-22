package org.purpura.apimg.dto.empresa.pix;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para requisições de criação/atualização de chave Pix da empresa.")
public class ChavePixRequestDTO {
    @Schema(description = "Nome da chave Pix.", example = "Conta Principal")
    @NotNull(message = "O nome da chave pix precisa ser informado e não nula")
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome da chave pix deve ter no mínimo dois caracteres que não sejam espaços.")
    private String nome;

    @Schema(description = "Valor da chave Pix.", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "A chave pix precisa ser informada e não nula")
    @Pattern(regexp = "\\S{5,20}", message = "A chave pix deve ter entre 5 e 20 caracteres alfanuméricos latinos ou especiais")
    private String chave;
}
