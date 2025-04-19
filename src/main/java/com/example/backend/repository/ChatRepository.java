package com.example.backend.repository;


import com.example.backend.DTO.ChatResponse;
import com.example.backend.model.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("""
            SELECT new com.example.backend.DTO.ChatResponse(
                        c.id,
                        c.title,
                        c.type,
                        c.dateCreated
            )
            FROM user_chat uc
            JOIN uc.chat c
            WHERE uc.user.id = :userId
            """)
    List<ChatResponse> findUserChats(@Param("userId") long userId);

}