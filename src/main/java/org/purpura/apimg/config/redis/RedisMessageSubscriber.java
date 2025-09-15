package org.purpura.apimg.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.purpura.apimg.dto.conversa.MessageRequestDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisMessageSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void onMessage(String message, String channel) {
        try {
            MessageRequestDTO dto = objectMapper.readValue(message, MessageRequestDTO.class);
            messagingTemplate.convertAndSend("/topic/chat." + dto.getChatId(), dto);
        } catch (Exception e) {
            throw new RuntimeException("Could not deserialize Redis message", e);
        }
    }
}
