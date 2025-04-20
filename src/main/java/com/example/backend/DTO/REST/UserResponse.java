package com.example.backend.DTO.REST;

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
@Schema(description = "Ответ пользователя")
public class UserResponse {
    private long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String username;

    private LocalDateTime dateCreated;

}
