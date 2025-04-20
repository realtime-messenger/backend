package com.example.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    public void greeting(
            @Payload
            HelloMessage message,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {

        String userId = headerAccessor.getSessionAttributes().get("userId").toString();
        System.out.println(userId);
        System.out.println(message.getName());

    }
}