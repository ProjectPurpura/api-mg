package org.purpura.apimg.model.conversa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mensagens")
public class MessageModel {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();


    private String chatId;
    private String senderId;
    private String content; // markdown

    @Builder.Default
    private boolean read = false;

    @Builder.Default
    private Long timestamp = System.currentTimeMillis();
}
