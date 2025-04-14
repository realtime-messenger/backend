package com.example.backend.userChat;

import com.example.backend.chat.Chat;
import com.example.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "user_chat")
public class UserChat {
    @Id
    long id;

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
    }
}
