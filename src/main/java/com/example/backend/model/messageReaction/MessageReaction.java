package com.example.backend.model.messageReaction;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageReaction {
    @NotNull
    private String userId;
    @NotNull
    private String chatId;
    @NotNull
    private String messageId;
    @NotNull
    private String reaction;
    @NotNull
    private LocalDateTime dateCreated;

    public MessageReaction (
            String userId,
            String chatId,
            String messageId,
            String reaction
    ) {
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.reaction = reaction;
        this.dateCreated = LocalDateTime.now();
    }
}
