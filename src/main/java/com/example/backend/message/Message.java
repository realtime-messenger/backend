package com.example.backend.message;

import com.example.backend.chat.Chat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

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