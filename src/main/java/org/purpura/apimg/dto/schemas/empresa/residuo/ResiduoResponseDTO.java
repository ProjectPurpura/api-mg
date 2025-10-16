package org.purpura.apimg.dto.schemas.empresa.residuo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.purpura.apimg.model.empresa.TipoUnidade;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resíduo da empresa")
public class ResiduoResponseDTO implements java.io.Serializable {
    @Schema(description = "Identificador único do resíduo (UUID)", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    @Schema(description = "Nome do resíduo", example = "Cana de açúcar")
    private String nome;
    @Schema(description = "Descrição detalhada do resíduo", example = "Cana de açúcar do estado de Minas Gerais")
    private String descricao;
    @Schema(description = "Preço do resíduo", example = "10.50")
    private Double preco;
    @Schema(description = "Peso do resíduo", example = "3.6")
    private Double peso;
    @Schema(description = "Unidade de medida do resíduo", example = "KG")
    private TipoUnidade tipoUnidade;
    @Schema(description = "Quantidade em estoque do resíduo", example = "100")
    private Long estoque;
    @Schema(description = "URL da foto do resíduo", example = "https://www.naturalcura.com.br/wp-content/uploads/2017/12/benef%C3%ADcios-da-cana-de-a%C3%A7%C3%BAcar.jpg")
    private String urlFoto;
    @Schema(description = "ID do endereço do CD do resíduo. (UUID)", example = "123e4567-e89b-12d3-a456-426614174000")
    private String idEndereco;
    @Schema(description = "ID da chave pix do CC da empresa. (UUID)", example = "123e45A7-e89b-42d3-a456-426b14574000")
    private String idChavePix;

    @Schema(description = "CNPJ da empresa que possui o resíduo", example = "12345678000195")
    private String cnpjEmpresa;
}
