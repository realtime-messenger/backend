package com.example.backend.repository;

import com.example.backend.model.messageStatus.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageStatusRepository extends MongoRepository<MessageStatus, String> {

    Optional<MessageStatus> findMessageStatusByMessageIdAndUserId(String messageId, String userId);
}