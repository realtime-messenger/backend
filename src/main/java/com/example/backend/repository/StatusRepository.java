package com.example.backend.repository;

import com.example.backend.model.userMessageStatus.UserMessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<UserMessageStatus, Long> {
    UserMessageStatus findMessageStatusByUserIdAndMessageId(long userId, long messageId);
}