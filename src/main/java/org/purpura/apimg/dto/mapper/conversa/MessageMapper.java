package org.purpura.apimg.dto.mapper.conversa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageRequestDTO;
import org.purpura.apimg.dto.schemas.conversa.mensagem.MessageResponseDTO;
import org.purpura.apimg.model.conversa.MessageModel;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper extends BeanUtilMapper<MessageModel, MessageRequestDTO, MessageResponseDTO> {

    public MessageMapper() {
        super(MessageModel.class, MessageResponseDTO.class);
    }
}
