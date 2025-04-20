package com.example.backend.DTO.REST;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Schema(description = "Запрос на вход")
public class JwtRequest {
    @Schema(description = "Логин пользователя", example = "gladyshdd")
    @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 255 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Пароль пользователя", example = "ASDhj91ahsd")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 5 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}