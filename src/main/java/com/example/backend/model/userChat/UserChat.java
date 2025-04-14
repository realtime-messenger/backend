package com.example.backend.model.userChat;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "user_chat")
public class UserChat extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public Chat chat;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;

    public UserChat () {}

    public UserChat (
            Chat chat,
            User user
    ) {
        this.chat = chat;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
    }
}
