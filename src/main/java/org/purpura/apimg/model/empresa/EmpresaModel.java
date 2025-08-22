package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Empresa")
public class EmpresaModel {
    @Id
    private String cCnpj;
    private String cNmEmpresa;
    private String cEmail;
    private String cTelefone;

    private boolean bAtivo = true;
    private List<EnderecoModel> lsEnderecos;
    private List<ChavePixModel> lsChavesPix;
    private List<ResiduoModel> lsResiduos;
}
