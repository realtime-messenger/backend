package com.example.backend.model.message;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "message")
@Getter
public class Message extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
    @Column(name = "chat_id", insertable = false, updatable = false)
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "text", nullable = false)
    private String text;

    public Message() {}

    public Message(
            Chat chat,
            User user,
            String text
    ) {
        this.chat = chat;
        this.user = user;
        this.text = text;

        this.userId = user.getId();
        this.chatId = chat.getId();

        this.dateCreated = LocalDateTime.now();
    }

}