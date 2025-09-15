package org.purpura.apimg.model.conversa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "conversas")
public class ChatModel {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private List<String> participants; // [cnpj-A, cnpj-B]

    @Builder.Default
    private String lastMessageId = null;

    @Builder.Default
    private Long updatedTimestamp = System.currentTimeMillis();
}
