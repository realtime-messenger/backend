package com.example.backend.DTO.response;

import com.example.backend.model.chat.ChatType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Ответ чата")
public class ChatExtendedResponse extends ChatResponse {

    @Nullable
    private MessageExtendedResponse lastMessage;

    public ChatExtendedResponse(
            long id,
            String title,
            ChatType type,
            LocalDateTime dateCreated,
            MessageExtendedResponse lastMessage
    ) {
        super(id, title, type, dateCreated);
        this.lastMessage=lastMessage;
    }
}
