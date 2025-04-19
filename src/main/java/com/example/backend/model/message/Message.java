package com.example.backend.model.message;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.chat.Chat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "message")
public class Message extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
    @Column(name = "chat_id", insertable = false, updatable = false)
    private Long chatId;

    @Column(name = "text", nullable = false)
    private String text;

    public Message() {}

    public Message(
            Chat chat,
            String text
    ) {
        this.chat = chat;
        this.text = text;
        this.dateCreated = LocalDateTime.now();
    }

}