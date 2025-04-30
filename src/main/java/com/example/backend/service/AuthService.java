package com.example.backend.service;

import com.example.backend.DTO.request.JwtRequest;
import com.example.backend.DTO.response.JwtResponse;
import com.example.backend.exceptions.JwtException;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.exceptions.WrongPasswordException;
import com.example.backend.model.user.User;
import com.example.backend.service.crud.UserCrudService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserCrudService userCrudService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserCrudService userCrudService, JwtService jwtService) {
        this.userCrudService = userCrudService;
        this.jwtService = jwtService;
    }

    public JwtResponse login(
            @NonNull
            JwtRequest authRequest
    ) throws WrongPasswordException {
        final User user = userCrudService.getByUsername(authRequest.getUsername());

        if (user.getPassword().equals(authRequest.getPassword())) {
            return this.getJwtToken(user);
        } else {
            throw new WrongPasswordException();
        }
    }

    public JwtResponse getJwtToken (
            User user
    ) {
        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final Long id = Long.parseLong(claims.getSubject());
            final User user = userCrudService.getById(id).orElseThrow(UserNotFoundException::new);
            final String accessToken = jwtService.generateAccessToken(user);
            final String newRefreshToken = jwtService.generateRefreshToken(user);
            return new JwtResponse(accessToken, newRefreshToken);
        }
        throw new JwtException();
    }
}