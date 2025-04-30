package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "Ответ контента сообщения")
public class MessageContentResponse {
    private String type;
    private String url;
}
