package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.purpura.apimg.common.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.UUID;

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

    @Builder.Default
    private String urlFoto = Constants.DEFAULT_URL_FOTO;

    @Builder.Default
    private ArrayList<EnderecoModel> enderecos = new ArrayList<>();

    @Builder.Default
    private ArrayList<ChavePixModel> chavesPix = new ArrayList<>();

    @Builder.Default
    private ArrayList<ResiduoModel> residuos = new ArrayList<>();

    @Builder.Default
    private String userHash = UUID.randomUUID().toString();
}
