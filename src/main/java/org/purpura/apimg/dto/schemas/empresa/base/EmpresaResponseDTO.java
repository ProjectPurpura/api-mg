package org.purpura.apimg.dto.schemas.empresa.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de resposta para dados de empresa.")
public class EmpresaResponseDTO implements java.io.Serializable {
    @Schema(description = "CNPJ da empresa, o seu ID.", example = "12345678000195")
    private String cnpj;
    @Schema(description = "Telefone da empresa.", example = "11999999999")
    private String telefone;
    @Schema(description = "E-mail da empresa.", example = "contato@empresa.com")
    private String email;
    @Schema(description = "Nome da empresa.", example = "Empresa Exemplo")
    private String nome;
    @Schema(description = "URL da foto da empresa.", example = "https://empresa.com/foto.png")
    private String urlFoto;
}
