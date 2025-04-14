package com.example.backend.model.userMessageReaction;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "user_message_reaction")
public class UserMessageReaction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "reaction", nullable = false)
    private String reaction;

    public UserMessageReaction() {}

    public UserMessageReaction(
            Message message,
            User user,
            String reaction
    ) {
        this.message = message;
        this.user = user;
        this.reaction = reaction;
        this.dateCreated = LocalDateTime.now();
    }

}