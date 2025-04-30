package com.example.backend.controller;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.model.user.User;
import com.example.backend.service.crud.ChatCrudService;
import com.example.backend.service.crud.UserCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/chat")
@Tag(name = "Чаты")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private final ChatCrudService chatCrudService;
    private final UserCrudService userCrudService;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatController(ChatCrudService chatCrudService, UserCrudService userCrudService, ChatMapper chatMapper) {
        this.chatCrudService = chatCrudService;
        this.userCrudService = userCrudService;
        this.chatMapper = chatMapper;
    }


    @Operation(summary = "Получить чаты пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<ChatResponse>> getChats()  {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userCrudService.getById(id).orElseThrow(UserNotFoundException::new);

        var result = chatCrudService.getChatsByUserId(String.valueOf(user.getId()));

        return ResponseEntity.ok(
            chatMapper.toListChatResponse(result)
        );
    }
}
