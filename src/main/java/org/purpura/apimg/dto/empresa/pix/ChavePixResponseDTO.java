package org.purpura.apimg.dto.empresa.pix;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChavePixResponseDTO {
    @Schema(description = "Identificador único da chave Pix", example = "c1a2b3c4-d5e6-7890-abcd-1234567890ef")
    private String id;
    @Schema(description = "Valor da chave Pix", example = "felix@email.com")
    private String chave;
    @Schema(description = "Apelido da chave Pix", example = "Chave principal")
    private String nome;
}
