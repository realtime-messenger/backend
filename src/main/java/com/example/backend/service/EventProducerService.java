package com.example.backend.service;

import com.example.backend.DTO.event.IEvent;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducerService {

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;


    @Autowired
    public EventProducerService(SimpMessagingTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    public void produceEvent(
            String destination,
            IEvent event
    ) {
        String json;
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }
        template.convertAndSend(destination, json);
    }

    // Клиенты подписываются на топик /topic/user/{userId} что бы получать ивенты адресованные персонально им
    // Пример: Был создан новый чат
    public void produceEventToUser(
            User user,
            IEvent event
    ) {
        String json;
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }
        template.convertAndSend("/topic/" + "user" + user.getId(), json);
    }

    // Клиенты подписываются на топик /topic/chat/{chatId} что бы получать ивенты которые нужно получить всем участникам
    // чата с chatId={chatId}
    // Пример: Новое сообщение в чате; Удалено сообщение у всех участников чата; Изменились реакции; Пользователь начал
    // печатать в чат
    public void produceEventToChat(
            Chat chat,
            IEvent event
    ) {
        String json;
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }
        template.convertAndSend("/topic/" + "chat" + chat.getId(), json);
    }

    // Клиенты подписываются на топик /topic/user-online/{userId} что бы получать ивенты об онлайне конкретного
    // пользователя
    public void produceEventToOnlineTopic(
            long userId,
            IEvent event
    ) {
        String json;
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }
        template.convertAndSend("/topic/" + "user-online" + userId, json);
    }

    public void produceEvent(
            String destination,
            String payload
    ) {
        template.convertAndSend(destination, payload);
    }
}
