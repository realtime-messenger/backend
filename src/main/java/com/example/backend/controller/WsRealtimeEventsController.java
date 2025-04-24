package com.example.backend.controller;

import com.example.backend.DTO.command.TypingRequest;
import com.example.backend.DTO.event.UserTypingEvent;
import com.example.backend.model.chat.Chat;
import com.example.backend.service.ChatService;
import com.example.backend.service.EventProducerService;
import com.example.backend.service.UserChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WsRealtimeEventsController {

    private final EventProducerService eventProducerService;
    private final UserChatService userChatService;
    private final ChatService chatService;

    public WsRealtimeEventsController(EventProducerService eventProducerService, UserChatService userChatService1, ChatService chatService) {
        this.eventProducerService = eventProducerService;
        this.userChatService = userChatService1;
        this.chatService = chatService;
    }


    @MessageMapping("/ping-typing")
    public void deleteReaction(
            @Payload
            TypingRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        if (!userChatService.userBelongsToChat(userId, request.getChatId())) {
            return;
        }

        Chat chat = chatService.getById(request.getChatId());

        UserTypingEvent event = new UserTypingEvent();
        event.setUserId(userId);
        event.setChatId(request.getChatId());

        eventProducerService.produceEventToChat(chat, event);
    }
}