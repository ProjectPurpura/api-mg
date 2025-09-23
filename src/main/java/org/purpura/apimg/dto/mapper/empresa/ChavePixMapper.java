package org.purpura.apimg.dto.mapper.empresa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixResponseDTO;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.springframework.stereotype.Component;

@Component
public class ChavePixMapper extends BeanUtilMapper<ChavePixModel, ChavePixRequestDTO, ChavePixResponseDTO> {
    public ChavePixMapper() {
        super(ChavePixModel.class, ChavePixResponseDTO.class);
    }
}
