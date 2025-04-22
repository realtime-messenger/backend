package com.example.backend.service;

import com.example.backend.DTO.event.IEvent;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
//        this.objectMapper = new ObjectMapper();
//        this.objectMapper.registerModule(new JavaTimeModule());

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
        System.out.println("AYO");
        System.out.println(json);
        template.convertAndSend("/queue/user/" + user.getId(), json);
    }

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
        template.convertAndSend("/queue/chat/" + chat.getId(), json);
    }

    public void produceEvent(
            String destination,
            String payload
    ) {
        template.convertAndSend(destination, payload);
    }
}
