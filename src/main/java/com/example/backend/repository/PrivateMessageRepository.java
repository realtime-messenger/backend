package com.example.backend.repository;

import com.example.backend.model.chat.PublicChat;
import com.example.backend.model.message.PrivateMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMessageRepository extends MongoRepository<PrivateMessage, String> {

}