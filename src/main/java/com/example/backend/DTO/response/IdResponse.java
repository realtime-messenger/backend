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
@Schema(description = "Ответ с id пользователя")
public class IdResponse {
    private long id;

    public IdResponse(
            long id
    ) {
        this.id=id;
    }
}
