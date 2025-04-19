package com.example.backend.controller;

import com.example.backend.DTO.ChatResponse;
import com.example.backend.repository.ChatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/chat")
@Tag(name = "Чаты")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Operation(summary = "Получить чаты пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<ChatResponse>> registration()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        var result = chatRepository.findUserChats(Long.parseLong(id));

        return ResponseEntity.ok(result);
    }
}
