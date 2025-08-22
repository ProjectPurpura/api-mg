package org.purpura.apimg.dto.empresa;

import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.beans.BeanUtils;

public class EmpresaResponseDTO {
    private String cnpj;
    private String telefone;
    private String email;
    private String name;

    public EmpresaResponseDTO(EmpresaModel empresaModel) {
        BeanUtils.copyProperties(empresaModel, this);
    }
}
