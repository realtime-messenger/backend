package com.example.backend.repository;

import com.example.backend.model.message.Message;
import com.example.backend.model.messageStatus.MessageStatus;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> getBaseMessagesByChatId(String chatId);

    @Aggregation(pipeline = {
            "{ '$match': { 'chatId' : ?0 } }",
            "{ '$sort' : { 'dateCreated' : 1 } }",
            "{ '$skip' : ?1 }",
            "{ '$limit' : ?2 }"
    })
    List<Message> getBaseMessagesByChatId(String chatId, long skip, long limit);

}