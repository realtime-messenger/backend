package com.example.backend.repository;

import com.example.backend.model.messageStatus.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageStatusRepository extends MongoRepository<MessageStatus, String> {

    Optional<MessageStatus> findMessageStatusByMessageIdAndUserId(String messageId, String userId);

    List<MessageStatus> findMessageStatusByMessageId(String messageId);

    List<MessageStatus> findMessageStatusesByMessageIdAndUserId(String messageId, String userId);
}