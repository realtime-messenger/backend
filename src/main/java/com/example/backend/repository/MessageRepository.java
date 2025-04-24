package com.example.backend.repository;


import com.example.backend.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findFirstByChatIdOrderByIdDesc(long chatId);

    @Query("""
            SELECT m
            FROM message m
            JOIN user_message_status ums ON ums.messageId = m.id
            WHERE m.chatId = :chatId
            AND ums.userId = :userId
            AND ums.isDeleted = false
            ORDER BY m.dateCreated DESC
            LIMIT :limit
            OFFSET :skip
            """)
    List<Message> findNonDeletedMessagesByChatId(
            @Param("chatId")
            long chatId,
            @Param("userId")
            long userId,
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
            JOIN user_message_status ums ON ums.messageId = m.id
            WHERE m.chatId = :chatId
            AND ums.userId = :userId
            AND ums.isDeleted = false
            ORDER BY m.dateCreated DESC
            LIMIT :limit
            OFFSET :skip
            """)
    Optional<Message> findMessageByChatId(
            @Param("chatId")
            long chatId,
            @Param("userId")
            long userId,
            @Param("skip")
            long skip,
            @Param("limit")
            long limit
    );
}