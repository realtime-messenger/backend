package com.example.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Даниил")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 2 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Гладыш")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 2 до 50 символов")
    @NotBlank(message = "Фамилия пользователя не может быть пустой")
    private String lastName;

    @Schema(description = "Отчество пользователя", example = "Дмитриевич")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 2 до 50 символов")
    private String middleName;


    @Schema(description = "Логин пользователя", example = "gladyshdd")
    @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    private String username;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;
}


