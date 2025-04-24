package com.example.backend.security;


import com.example.backend.service.JwtService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class WebSocketInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;

    public WebSocketInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {
        System.out.println("INTERCEPTIN A WEBSCOKET HANDSHAKE");
        String query = request.getURI().getQuery();

        System.out.println(query);

        if (query == null || !query.contains("token=")) {
            System.out.println("Bad query");
            return false;
        }

        String token = query.split("token=")[1].split("&")[0];

        if (token == null) {
            System.out.println("Token is null");
            return false;
        }

        try {
            if (!jwtService.validateAccessToken(token)) {
                System.out.println("Token is not valid");
                return false;

            }
            String userId = jwtService.getAccessClaims(token).getSubject();
            attributes.put("userId", userId); // Сохраняем данные пользователя для использования в WebSocketHandler
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception
    ) {}
}