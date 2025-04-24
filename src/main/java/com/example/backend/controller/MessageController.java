package com.example.backend.controller;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.model.user.User;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
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
        User user = userService.getCurrentUser();

        // TODO ignore deleted messages

        var result = messageService.getUserMessages(
                user,
                chatId,
                skip,
                limit
        );
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить последние сообщения каждого чата пользователя")
    @GetMapping("/lasts")
    public ResponseEntity<Collection<MessageExtendedResponse>> getLastsMessages(
    )  {
        User user = userService.getCurrentUser();

        // TODO ignore deleted messages

        var result = messageService.getLastMessages(
            user
        );
        return ResponseEntity.ok(result);
    }

}