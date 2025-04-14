package com.example.backend.model.chat;

import com.example.backend.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "chat")
public class Chat extends BaseEntity {
    @NotNull
    private ChatType type;

    @Column(name = "title", nullable = true)
    private String title;

    public Chat() {}

    public Chat(
            ChatType type,
            String title
    ) {
        this.type = type;
        this.title = title;
        this.dateCreated = LocalDateTime.now();
    }

    public ChatType getChatType() {
        return this.type;
    }

    public void setChatType(ChatType type) {
        this.type = type;
    }

    public String getTitle() {
        if (this.type == ChatType.PRIVATE) {
            throw new PrivateChatTitleAccessException("You can not access title of private chat");
        } else {
            return this.title;
        }
    }

    public void setTitle (String title) {
        if (this.type == ChatType.PRIVATE) {
            throw new PrivateChatTitleAccessException("You can not access title of private chat");
        } else {
            this.title = title;
        }
    }

    public LocalDateTime getCreatedAt() {
        return dateCreated;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.dateCreated = createdAt;
    }
}

