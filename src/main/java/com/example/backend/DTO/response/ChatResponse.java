package com.example.backend.DTO.response;

import com.example.backend.model.chat.ChatType;
import io.swagger.v3.oas.annotations.media.Schema;
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

    private UserResponse interlocutor;

    public ChatResponse(
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
