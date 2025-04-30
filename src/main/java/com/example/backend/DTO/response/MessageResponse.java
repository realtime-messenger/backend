package com.example.backend.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Schema(description = "Ответ сообщения")
public class MessageResponse {
    private long id;
    private long userId;
    private long chatId;
    private String text;

    private List<MessageContentResponse> photo;
    private List<MessageContentResponse> video;
    private List<ReactionResponse> reaction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
