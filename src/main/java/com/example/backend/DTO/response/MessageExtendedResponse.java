package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(description = "Расширенный ответ сообщения")
public class MessageExtendedResponse extends MessageResponse {
    private boolean isRead;

    public MessageExtendedResponse(String id, String chatId, String text, LocalDateTime dateCreated) {
        super(id, chatId, text, dateCreated);
    }

    public boolean getIsRead () {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead=isRead;
    }

}
