package com.example.backend.service;

import com.example.backend.DTO.REST.JwtRequest;
import com.example.backend.DTO.REST.JwtResponse;
import com.example.backend.exceptions.JwtException;
import com.example.backend.exceptions.WrongPasswordException;
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
    ) throws WrongPasswordException {
        final User user = userService.getByUsername(authRequest.getUsername());

        if (user.getPassword().equals(authRequest.getPassword())) {
            return this.getJwtToken(user);
        } else {
            throw new WrongPasswordException();
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
            final Long id = Long.parseLong(claims.getSubject());
            final User user = userService.getById(id);
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String newRefreshToken = jwtProvider.generateRefreshToken(user);
            return new JwtResponse(accessToken, newRefreshToken);
        }
        throw new JwtException();
    }
}