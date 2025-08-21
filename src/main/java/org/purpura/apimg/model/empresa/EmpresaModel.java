package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Empresa")
public class EmpresaModel {
    @Id
    private String cCdEmpresa;
    private String cNmEmpresa;
    private String cEmail;
    private String cTelefone;

    private List<EnderecoModel> lsEnderecos;
    private List<ChavePixModel> lsChavesPix;
    private List<ResiduoModel> lsResiduos;
}
