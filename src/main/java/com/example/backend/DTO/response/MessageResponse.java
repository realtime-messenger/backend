package com.example.backend.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private UserResponse user;
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    public MessageResponse(
            long id,
            long chatId,
            UserResponse user,
            String text,
            LocalDateTime dateCreated
    ) {
        this.id=id;
        this.chatId=chatId;
        this.user=user;
        this.text=text;
        this.dateCreated=dateCreated;
    }
}
