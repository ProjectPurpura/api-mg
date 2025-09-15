package org.purpura.apimg.dto.conversa;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatResponseDTO {
    private String chatId;
    private List<String> participants;

    @Builder.Default
    private String lastMessagePreview = null;  // optional, could be null if new chat

    @Builder.Default
    private Long lastUpdated = System.currentTimeMillis();

    @Builder.Default
    private int unreadCount = 0;            // computed per user
}