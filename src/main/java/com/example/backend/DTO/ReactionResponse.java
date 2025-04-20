package com.example.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Schema(description = "Ответ реакции")
public class ReactionResponse {
    private long id;
    private long messageId;
    private long userId;
    private String reaction;
    private LocalDateTime dateCreated;
}
