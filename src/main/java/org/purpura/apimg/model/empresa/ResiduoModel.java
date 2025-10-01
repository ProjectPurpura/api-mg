package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ResiduoModel {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private Double peso;
    private Double preco;
    private Long estoque;
    private TipoUnidade tipoUnidade;
    private String urlFoto;
}
