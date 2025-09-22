package org.purpura.apimg.dto.empresa.base;

import lombok.Data;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.beans.BeanUtils;

@Data
public class EmpresaResponseDTO {
    private String cnpj;
    private String telefone;
    private String email;
    private String nome;
    private String urlFoto;

    public EmpresaResponseDTO(EmpresaModel empresaModel) {
        BeanUtils.copyProperties(empresaModel, this);
    }
}
