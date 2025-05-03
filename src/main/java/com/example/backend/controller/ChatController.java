package com.example.backend.controller;

import com.example.backend.DTO.response.BaseChatResponse;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.mapper.PrivateChatMapper;
import com.example.backend.mapper.PublicChatMapper;
import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.chat.PrivateChat;
import com.example.backend.model.chat.PublicChat;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/chat")
@Tag(name = "Чаты")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private final ChatCrudService chatCrudService;
    private final UserCrudService userCrudService;
    private final PrivateChatMapper privateChatMapper;
    private final PublicChatMapper publicChatMapper;

    @Autowired
    public ChatController(ChatCrudService chatCrudService, UserCrudService userCrudService, PrivateChatMapper privateChatMapper, PublicChatMapper publicChatMapper) {
        this.chatCrudService = chatCrudService;
        this.userCrudService = userCrudService;
        this.privateChatMapper = privateChatMapper;
        this.publicChatMapper = publicChatMapper;
    }


    @Operation(summary = "Получить чаты пользователя")
    @GetMapping("")
    public ResponseEntity<Collection<BaseChatResponse>> getChats()  {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userCrudService.getById(id).orElseThrow(UserNotFoundException::new);

        var chats = chatCrudService.getChatsByUserId(String.valueOf(user.getId()));

        List<BaseChatResponse> result = new ArrayList<>();

        for (BaseChat chat : chats) {
            if (chat instanceof PrivateChat) {
                result.add(
                    privateChatMapper.toChatResponse((PrivateChat) chat)
                );
            }
            if (chat instanceof PublicChat) {
                result.add(
                    publicChatMapper.toChatResponse((PublicChat) chat)
                );
            }
        }

        return ResponseEntity.ok(result);
    }
}
