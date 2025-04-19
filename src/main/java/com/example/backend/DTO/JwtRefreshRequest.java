package com.example.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "Запрос на обновление токена")
public class JwtRefreshRequest {

    public String refreshToken;

}