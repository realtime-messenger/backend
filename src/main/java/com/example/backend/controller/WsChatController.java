package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WsChatController {
    private final EventProducerService eventProducerService;


    @Autowired
    public WsChatController(EventProducerService eventProducerService) {
        this.eventProducerService = eventProducerService;
    }

    @MessageMapping("/start-chat")
    public void startChat(
            @Payload
            StartChatRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // написать сервис проверяющий наличие приватного чата с такими пользователями
        // написать сервис создающий тако й чат
        // отправлять ивент о создании такого чата всем кого он касается

        System.out.println(userId);

        eventProducerService.produceEvent(
                "/topic/broadcast",
                "hi all"
        );
    }
}