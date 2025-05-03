package com.example.backend.controller;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.service.business.MessageService;
import com.example.backend.service.crud.MessageStatusCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;


@RestController
@RequestMapping("/api/v1/message")
@Tag(name = "Сообщения")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    private final MessageService messageService;
    private final MessageStatusCrudService messageStatusCrudService;

    public MessageController(MessageService messageService, MessageStatusCrudService messageStatusCrudService) {
        this.messageService = messageService;
        this.messageStatusCrudService = messageStatusCrudService;
    }

    @Operation(summary = "Получить сообщения чата пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<MessageExtendedResponse>> getMessages(
            @RequestParam("chatId")
            String chatId,
            @RequestParam("skip")
            int skip,
            @RequestParam("limit")
            int limit
    )  {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(
            messageService.getChatMessages(
                    String.valueOf(id),
                    chatId,
                    skip,
                    limit
            )
        );
    }

    @Operation(summary = "Получить по одному сообщению из каждого чата пользователя")
    @GetMapping("/lasts")
    public ResponseEntity<Collection<MessageExtendedResponse>> getLastMessages(
    )  {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(messageService.getLastMessages(String.valueOf(id)));
    }

}