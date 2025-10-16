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
public class EnderecoModel {
    @Id
    @Builder.Default
    private String id = java.util.UUID.randomUUID().toString();
    private String nome;
    private Integer numero;
    private String cep;
    private String complemento;
}
