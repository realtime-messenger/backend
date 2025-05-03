package com.example.backend.controller;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.service.business.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/message")
@Tag(name = "Сообщения")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Получить сообщения чата пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<MessageExtendedResponse>> getMessages(
            @RequestParam("chatId")
            int chatId,
            @RequestParam("skip")
            int skip,
            @RequestParam("limit")
            int limit
    )  {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(
            messageService.getChatMessages(
                    String.valueOf(id),
                    String.valueOf(chatId),
                    skip,
                    limit
            )
        );
    }

}