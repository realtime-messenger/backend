package com.example.backend.service;

import com.example.backend.DTO.JwtRequest;
import com.example.backend.DTO.JwtResponse;
import com.example.backend.model.user.User;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtProvider;

    @Autowired
    public AuthService(UserService userService, JwtService jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponse login(
            @NonNull
            JwtRequest authRequest
    ) throws AuthException {
        final User user = userService.getByUsername(authRequest.getUsername());

        if (user.getPassword().equals(authRequest.getPassword())) {
            return this.getJwtToken(user);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtResponse getJwtToken (
            User user
    ) {
        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final User user = userService.getByUsername(username);
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String newRefreshToken = jwtProvider.generateRefreshToken(user);
            return new JwtResponse(accessToken, newRefreshToken);
        }
        throw new AuthException("Невалидный JWT токен");
    }
}