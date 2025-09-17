package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

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
    private String urlFoto = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fanitawatkins.com%2Fwp-content%2Fuploads%2F2016%2F02%2FGeneric-Profile-1600x1600.png&f=1&nofb=1&ipt=c6e3c868a750bbffccb07fbaa319aaf276c6d2926b817063718f1e87e593064a";

    @Builder.Default
    private ArrayList<EnderecoModel> enderecos = new ArrayList<>();

    @Builder.Default
    private ArrayList<ChavePixModel> chavesPix = new ArrayList<>();

    @Builder.Default
    private ArrayList<ResiduoModel> residuos = new ArrayList<>();
}
