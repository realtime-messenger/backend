package com.example.backend.controller;

import com.example.backend.DTO.request.CreateUserRequest;
import com.example.backend.DTO.request.JwtRefreshRequest;
import com.example.backend.DTO.request.JwtRequest;
import com.example.backend.DTO.response.JwtResponse;
import com.example.backend.model.user.User;
import com.example.backend.service.AuthService;
import com.example.backend.service.crud.UserCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthService authService;
    private final UserCrudService userCrudService;

    @Autowired
    public AuthController(AuthService authService, UserCrudService userCrudService) {
        this.authService = authService;
        this.userCrudService = userCrudService;
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/registration")
    public ResponseEntity<JwtResponse> registration(
            @RequestBody
            CreateUserRequest request
    ) throws AuthException {
        User user = userCrudService.create(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getMiddleName(),
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final JwtResponse token = authService.getJwtToken(
                user
        );
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Вход")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Обновить токены")
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRefreshRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}