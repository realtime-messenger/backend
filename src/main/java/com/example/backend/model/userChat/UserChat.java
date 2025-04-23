package com.example.backend.model.userChat;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "user_chat")
@Getter
public class UserChat extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    public Chat chat;

    @Column(name = "chat_id", insertable = false, updatable = false)
    private Long chatId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public UserChat () {}

    public UserChat (
            Chat chat,
            User user
    ) {
        this.chat = chat;
        this.user = user;
        this.chatId = chat.getId();
        this.userId = user.getId();
        this.dateCreated = LocalDateTime.now();
    }
}
