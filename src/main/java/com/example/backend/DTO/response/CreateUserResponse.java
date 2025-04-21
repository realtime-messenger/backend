package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Data
@Schema(description = "Ответ регистрации")
public class CreateUserResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private LocalDateTime dateCreated;

}