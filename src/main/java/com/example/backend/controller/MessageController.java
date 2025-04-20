package com.example.backend.controller;

import com.example.backend.DTO.MessageResponseExtended;
import com.example.backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/message")
@Tag(name = "Сообщения")
public class MessageController {


    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Получить сообщения чата пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<MessageResponseExtended>> getMessages(
            @RequestParam("chatId")
            int chatId,
            @RequestParam("skip")
            int skip,
            @RequestParam("linit")
            int limit
    )  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getName());

        var result = messageService.getUserMessages(
                userId,
                chatId,
                skip,
                limit
        );

        return ResponseEntity.ok(result);
    }

}