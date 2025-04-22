package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Schema(description = "Расширенный ответ сообщения")
public class MessageExtendedResponse extends MessageResponse {
    private boolean isRead;
    private List<ReactionResponse> reactions;

    public MessageExtendedResponse(
            long id,
            long chatId,
            long userId,
            String text,
            LocalDateTime dateCreated,
            boolean isRead,
            List<ReactionResponse> reactions
    ) {
        super(id, chatId, userId, text, dateCreated);
        this.isRead = isRead;
        this.reactions = reactions;
    }

    public void setIsRead(boolean isRead) {
        this.isRead=isRead;
    }

    public void setReactions(List<ReactionResponse> reactions) {
        this.reactions=reactions;
    }
}
