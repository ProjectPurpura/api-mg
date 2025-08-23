package org.purpura.apimg.dto.empresa;

import lombok.Data;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.beans.BeanUtils;

@Data
public class EmpresaResponseDTO {
    private String cnpj;
    private String telefone;
    private String email;
    private String nome;

    public EmpresaResponseDTO(EmpresaModel empresaModel) {
        BeanUtils.copyProperties(empresaModel, this);
    }
}
