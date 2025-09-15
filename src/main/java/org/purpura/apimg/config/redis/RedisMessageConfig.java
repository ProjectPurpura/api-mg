package org.purpura.apimg.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RedisMessageConfig {

    private final SimpMessagingTemplate messagingTemplate;

    public RedisMessageConfig(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // listen to all chat.* channels
        container.addMessageListener((message, pattern) -> {
            try {
                String channel = new String(message.getChannel(), StandardCharsets.UTF_8); // e.g. "chat.room1"
                String payload = new String(message.getBody(), StandardCharsets.UTF_8);

                // forward to STOMP topic with same name
                messagingTemplate.convertAndSend("/topic/" + channel, payload);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, new PatternTopic("chat.*"));

        return container;
    }
}
