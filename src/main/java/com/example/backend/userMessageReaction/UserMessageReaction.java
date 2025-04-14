package com.example.backend.userMessageReaction;

import com.example.backend.message.Message;
import com.example.backend.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "user_message_reaction")
public class UserMessageReaction {
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

    @Column(name = "reaction", nullable = false)
    private String reaction;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

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