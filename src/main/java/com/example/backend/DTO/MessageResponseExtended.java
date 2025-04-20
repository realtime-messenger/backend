package com.example.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(description = "Расширенный ответ сообщения")
public class MessageResponseExtended extends MessageResponse {
    private boolean isRead;
    private List<ReactionResponse> reactions;

    public MessageResponseExtended(
            long id,
            long chatId,
            String text,
            LocalDateTime dateCreated,
            boolean isRead,
            List<ReactionResponse> reactions
    ) {
        super(id, chatId, text, dateCreated);
        this.isRead = isRead;
        this.reactions = reactions;
    }
}
