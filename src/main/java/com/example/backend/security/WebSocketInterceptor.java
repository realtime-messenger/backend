package com.example.backend.security;


import com.example.backend.service.JwtService;
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
        String query = request.getURI().getQuery();

        if (query == null || !query.contains("token=")) {
            return false;
        }

        String token = query.split("token=")[1].split("&")[0];

        if (token == null) {
            return false;
        }

        try {
            if (!jwtService.validateAccessToken(token)) {
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