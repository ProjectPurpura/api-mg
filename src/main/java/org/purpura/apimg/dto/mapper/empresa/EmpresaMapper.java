package org.purpura.apimg.dto.mapper.empresa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.stereotype.Component;


@Component
public class EmpresaMapper extends BeanUtilMapper<EmpresaModel, EmpresaRequestDTO, EmpresaResponseDTO> {
    public EmpresaMapper() {
        super(EmpresaModel.class, EmpresaResponseDTO.class);
    }
}
