package com.example.backend.repository;

import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.chat.PrivateChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseChatRepository extends MongoRepository<BaseChat, String> {

}