package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

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
            UserResponse user,
            String text,
            LocalDateTime dateCreated,
            boolean isRead,
            List<ReactionResponse> reactions
    ) {
        super(id, chatId, user, text, dateCreated);
        this.isRead = isRead;
        this.reactions = reactions;
    }

    public boolean getIsRead () {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead=isRead;
    }

    public void setReactions(List<ReactionResponse> reactions) {
        this.reactions=reactions;
    }
}
