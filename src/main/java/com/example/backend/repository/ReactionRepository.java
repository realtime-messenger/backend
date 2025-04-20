package com.example.backend.repository;

import com.example.backend.model.userMessageReaction.UserMessageReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<UserMessageReaction, Long> {
    List<UserMessageReaction> findUserMessageReactionsByMessageId(long messageId);
}