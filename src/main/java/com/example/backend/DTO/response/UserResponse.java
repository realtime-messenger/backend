package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
