package com.example.backend.userMessageStatus;

import com.example.backend.message.Message;
import com.example.backend.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "user_message_status")
public class UserMessageStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public UserMessageStatus() {}

    public UserMessageStatus(
            Message message,
            User user
    ) {
        this.message = message;
        this.user = user;
        this.isDeleted = false;
        this.isRead = false;
        this.dateCreated = LocalDateTime.now();
    }

}