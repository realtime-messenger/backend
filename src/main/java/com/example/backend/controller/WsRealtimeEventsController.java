package com.example.backend.controller;

import com.example.backend.DTO.command.TypingRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WsRealtimeEventsController {

    @MessageMapping("/ping-online")
    public void setReaction(
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        // TODO

    }

    @MessageMapping("/ping-typing")
    public void deleteReaction(
            @Payload
            TypingRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        // TODO

    }
}