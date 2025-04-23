package com.example.backend.repository;

import com.example.backend.model.userMessageStatus.UserMessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<UserMessageStatus, Long> {
    Optional<UserMessageStatus> findMessageStatusByUserIdAndMessageId(long userId, long messageId);
}