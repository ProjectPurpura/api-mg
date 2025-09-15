package org.purpura.apimg.repository;

import org.purpura.apimg.model.conversa.ChatModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatModel, String> {
    List<ChatModel> findByParticipantsContains(String userId);
}
