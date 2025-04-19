package com.example.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Schema(description = "Запрос на вход")
public class JwtRequest {
    private String username;
    private String password;
}