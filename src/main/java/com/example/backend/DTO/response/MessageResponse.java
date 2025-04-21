package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Schema(description = "Ответ сообщения")
public class MessageResponse {
    private long id;
    private long chatId;
    private String text;
    private LocalDateTime dateCreated;

    public MessageResponse(
            long id,
            long chatId,
            String text,
            LocalDateTime dateCreated
    ) {
        this.id=id;
        this.chatId=chatId;
        this.text=text;
        this.dateCreated=dateCreated;
    }
}
