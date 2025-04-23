package com.example.backend.repository;


import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("""
            SELECT uc.chat
            FROM user_chat uc
            JOIN uc.chat c
            WHERE uc.user.id = :userId
            """)
    List<Chat> findUserChats(@Param("userId") long userId);

    @Query("""
            SELECT uc.user
            FROM user_chat uc
            JOIN uc.chat c
            WHERE uc.chat.id = :chatId
            """)
    List<User> findChatParticipants(@Param("chatId") long chatId);

    Optional<Chat> findChatById(Long id);

    Chat getChatById(Long id);
}