package com.example.backend.repository;


import com.example.backend.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findFirstByChatIdOrderByIdDesc(long chatId);

    @Query("""
            SELECT m
            FROM message m
            WHERE m.chatId = :chatId
            ORDER BY m.dateCreated DESC
            LIMIT :limit
            OFFSET :skip
            """)
    List<Message> findMessagesByChatId(
            @Param("chatId")
            long chatId,
            @Param("skip")
            long skip,
            @Param("limit")
            long limit
    );

    @Query("""
            SELECT m
            FROM message m
            WHERE m.chatId = :chatId
            ORDER BY m.dateCreated DESC
            LIMIT :limit
            OFFSET :skip
            """)
    Message findMessageByChatId(
            @Param("chatId")
            long chatId,
            @Param("skip")
            long skip,
            @Param("limit")
            long limit
    );
}