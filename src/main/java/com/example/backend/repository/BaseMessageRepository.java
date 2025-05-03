package com.example.backend.repository;

import com.example.backend.model.message.BaseMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMessageRepository extends MongoRepository<BaseMessage, String> {

    List<BaseMessage> getBaseMessagesByChatId(String chatId);

    @Query("{chatId: ?0}")
    List<BaseMessage> getBaseMessagesByChatId(String chatId, long skip, long limit);
}