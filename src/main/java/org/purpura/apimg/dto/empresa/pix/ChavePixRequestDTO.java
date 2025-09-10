package org.purpura.apimg.dto.empresa.pix;

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
public class ChavePixRequestDTO {
    @NotNull(message = "O nome da chave pix precisa ser informado e não nula")
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome da chave pix deve ter no mínimo dois caracteres que não sejam espaços.")
    private String nome;

    @NotNull(message = "A chave pix precisa ser informada e não nula")
    @Pattern(regexp = "\\S{5,20}", message = "A chave pix deve ter entre 5 e 20 caracteres alfanuméricos latinos ou especiais")
    private String chave;
}
