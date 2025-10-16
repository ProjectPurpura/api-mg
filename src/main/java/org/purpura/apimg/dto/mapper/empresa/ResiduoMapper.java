package org.purpura.apimg.dto.mapper.empresa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.purpura.apimg.model.empresa.ResiduoModel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ResiduoMapper extends BeanUtilMapper<ResiduoModel, ResiduoRequestDTO, ResiduoResponseDTO> {
    public ResiduoMapper() {
        super(ResiduoModel.class, ResiduoResponseDTO.class);
    }

    public ResiduoResponseDTO toResponse(ResiduoModel model, String empresaCnpj) {
        ResiduoResponseDTO response = super.toResponse(model);
        response.setEmpresaCnpj(empresaCnpj);
        return response;
    }

    public List<ResiduoResponseDTO> toResponseList(List<ResiduoModel> models, String empresaCnpj) {
        return models.stream()
                .map(m -> toResponse(m, empresaCnpj))
                .toList();
    }
}
