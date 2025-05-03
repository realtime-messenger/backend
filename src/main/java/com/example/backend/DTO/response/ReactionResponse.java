package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Schema(description = "Ответ реакции")
public class ReactionResponse {
    private String messageId;
    private long userId;
    private String chatId;
    private String reaction;
    private LocalDateTime dateCreated;
}
