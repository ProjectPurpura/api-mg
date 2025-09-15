package org.purpura.apimg.repository;

import org.purpura.apimg.model.conversa.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String> {
    List<MessageModel> findByChatIdOrderByTimestampDesc(String chatId);
}
