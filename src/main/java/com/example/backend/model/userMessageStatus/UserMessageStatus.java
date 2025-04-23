package com.example.backend.model.userMessageStatus;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "user_message_status")
@Getter
public class UserMessageStatus extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;
    @Column(name = "message_id", insertable = false, updatable = false)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    public UserMessageStatus() {}

    public UserMessageStatus(
            Message message,
            User user
    ) {
        this.message = message;
        this.user = user;
        this.messageId = message.getId();
        this.userId = user.getId();
        this.isDeleted = false;
        this.isRead = false;
        this.dateCreated = LocalDateTime.now();
    }
}