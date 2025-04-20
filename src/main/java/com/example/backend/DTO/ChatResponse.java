package com.example.backend.DTO;

import com.example.backend.model.chat.ChatType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Schema(description = "Ответ чата")
public class ChatResponse {
    private long id;
    private String title;
    private String type;
    private LocalDateTime dateCreated;

    @Nullable
    private MessageResponseExtended lastMessage;

    public ChatResponse (
            long id,
            String title,
            ChatType type,
            LocalDateTime dateCreated
    ) {
        this.id=id;
        this.title=title;
        this.type=type.toString();
        this.dateCreated=dateCreated;
    }
}
