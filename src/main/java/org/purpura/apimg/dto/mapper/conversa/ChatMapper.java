package org.purpura.apimg.dto.mapper.conversa;

import org.purpura.apimg.dto.mapper.base.BeanUtilMapper;
import org.purpura.apimg.dto.schemas.conversa.chat.ChatResponseDTO;
import org.purpura.apimg.dto.schemas.conversa.chat.CreateChatRequestDTO;
import org.purpura.apimg.model.conversa.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper extends BeanUtilMapper<ChatModel, CreateChatRequestDTO, ChatResponseDTO> {

    public ChatMapper() {
        super(ChatModel.class, ChatResponseDTO.class);
    }
}
