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
        System.out.println("INTERCEPTIN A WEBSCOKET HANDSHAKE");

        String query = request.getURI().getQuery();
        String token = null;
        if (query != null && query.contains("token=")) {
            token = query.split("token=")[1].split("&")[0];
        }

        if (token != null) {
            try {
                boolean isValid = jwtService.validateAccessToken(token);

                if (isValid) {
                    String userId = jwtService.getAccessClaims(token).getSubject();
                    attributes.put("userId", userId); // Сохраняем данные пользователя для использования в WebSocketHandler
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}