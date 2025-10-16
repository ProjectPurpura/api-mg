package org.purpura.apimg.dto.mapper.empresa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.purpura.apimg.model.empresa.ResiduoModel;
import org.springframework.stereotype.Component;


@Component
public class ResiduoMapper extends BeanUtilMapper<ResiduoModel, ResiduoRequestDTO, ResiduoResponseDTO> {
    public ResiduoMapper() {
        super(ResiduoModel.class, ResiduoResponseDTO.class);
    }

    @Override
    public ResiduoResponseDTO toResponse(ResiduoModel model) {
        return super.toResponse(model);
    }
}
