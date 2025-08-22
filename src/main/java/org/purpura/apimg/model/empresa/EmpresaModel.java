package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "empresas")
public class EmpresaModel {
    @Id
    private String cnpj;
    private String nome;
    private String email;
    private String telefone;

    private boolean ativo = true;
    private List<EnderecoModel> enderecos;
    private List<ChavePixModel> chavesPix;
    private List<ResiduoModel> residuos;
}
