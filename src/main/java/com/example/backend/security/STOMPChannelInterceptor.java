package com.example.backend.security;

import com.example.backend.service.OnlineService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.concurrent.ConcurrentHashMap;


public class STOMPChannelInterceptor implements ChannelInterceptor {

    private final OnlineService onlineService;

    public STOMPChannelInterceptor(OnlineService onlineService) {
        this.onlineService = onlineService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageType messageType = (SimpMessageType) message.getHeaders().get("simpMessageType");
        var attrs = message.getHeaders().get("simpSessionAttributes");
        ConcurrentHashMap<String, String> mapAttrs = (ConcurrentHashMap<String, String>) attrs;
        long userId = Long.parseLong(mapAttrs.get("userId"));
        String sessionId = (String) message.getHeaders().get("simpSessionId");

        if (messageType == SimpMessageType.CONNECT) {
            onlineService.pingUserOnline(
                    userId,
                    sessionId
            );
        }
        if (messageType == SimpMessageType.DISCONNECT) {
            onlineService.pingUserOffline(
                    userId,
                    sessionId
            );
        }

        return message;
    }
}