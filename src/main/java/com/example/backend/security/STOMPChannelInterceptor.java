package com.example.backend.security;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.concurrent.ConcurrentHashMap;

public class STOMPChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageType messageType = (SimpMessageType) message.getHeaders().get("simpMessageType");
        var attrs = message.getHeaders().get("simpSessionAttributes");
        ConcurrentHashMap<String, String> mapAttrs = (ConcurrentHashMap<String, String>) attrs;
        long userId = Long.parseLong(mapAttrs.get("userId"));
        String sessionId = (String) message.getHeaders().get("simpSessionId");

        if (messageType == SimpMessageType.CONNECT) {
            onUserConnect(userId, sessionId);
        }
        if (messageType == SimpMessageType.DISCONNECT) {
            onUserDisconnect(userId, sessionId);
        }

        return message;
    }


    public void onUserConnect (
            long userId,
            String sessionId
    ) {
        System.out.println("user with userid and sessionId connected " + userId + " " + sessionId);
    }

    public void onUserDisconnect(
            long userId,
            String sessionId
    ) {
        System.out.println("user with userid and sessionId disconnected " + userId + " " + sessionId);
    }
}