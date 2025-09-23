package org.purpura.apimg.dto.mapper.empresa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoResponseDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper extends BeanUtilMapper<EnderecoModel, EnderecoRequestDTO, EnderecoResponseDTO> {

    public EnderecoMapper() {
        super(EnderecoModel.class, EnderecoResponseDTO.class);
    }
}
