package com.example.backend.chat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private ChatType type;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public Chat() {}

    public Chat(
            ChatType type,
            String title
    ) {
        this.type = type;
        this.title = title;
        this.dateCreated = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

