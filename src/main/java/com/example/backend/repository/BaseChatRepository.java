package com.example.backend.repository;

import com.example.backend.model.chat.BaseChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseChatRepository extends MongoRepository<BaseChat, String> {

    @Query("{userId: ?0}")
    public List<BaseChat> getChatsByUserId(String userId);


}