package com.example.backend.controller;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.model.user.User;
import com.example.backend.service.ChatService;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/chat")
@Tag(name = "Чаты")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }


    @Operation(summary = "Получить чаты пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<ChatResponse>> getChats()  {
        User user = userService.getCurrentUser();

        var result = chatService.getUserChatsResponse(user);

        return ResponseEntity.ok(result);
    }
}
