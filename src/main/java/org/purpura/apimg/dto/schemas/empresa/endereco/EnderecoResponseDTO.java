package org.purpura.apimg.dto.schemas.empresa.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para respostas de endereço de empresa.")
public class EnderecoResponseDTO {
    @Schema(description = "Identificador único do endereço.", example = "e1234567-abcd-8901-2345-abcdef123456")
    private String id;

    @Schema(description = "Apelido do endereço.", example = "Matriz")
    private String nome;

    @Schema(description = "CEP do endereço, apenas números.", example = "01001000")
    private String cep;

    @Schema(description = "Complemento do endereço.", example = "Sala 101")
    private String complemento;

    @Schema(description = "Número do endereço.", example = "100")
    private Integer numero;
}

