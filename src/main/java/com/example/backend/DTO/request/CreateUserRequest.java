package com.example.backend.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "Запрос на регистрациюы")
public class CreateUserRequest {

    @Schema(description = "Имя пользователя", example = "Даниио")
    @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 255 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Гладыш")
    @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 255 символов")
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @Schema(description = "Отчество пользователя", example = "Дмитриевич")
    @Size(min = 5, max = 255, message = "Отчество должно содержать от 5 до 255 символов")
    private String middleName;

    @Schema(description = "Логин пользователя", example = "gladyshdd")
    @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 255 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Пароль пользователя", example = "ASDhj91ahsd")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 5 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

}