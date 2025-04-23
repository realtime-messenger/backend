package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class WsReactionController {
    private final MessageService messageService;
    private final UserService userService;
    private final UserChatService userChatService;
    private final ReactionService reactionService;
    private final ChatService chatService;


    @Autowired
    public WsReactionController(
            MessageService messageService,
            UserService userService,
            UserChatService userChatService,
            ReactionService reactionService,
            ChatService chatService) {
        this.messageService = messageService;
        this.userService = userService;
        this.userChatService = userChatService;
        this.reactionService = reactionService;
        this.chatService = chatService;
    }


    @MessageMapping("/set-reaction")
    public void setReaction(
            @Payload
            SetReactionRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        User user = userService.getById(userId);
        Message message = messageService.getById(request.getMessageId());

        if (!userChatService.userBelongsToChat(user.getId(), message.getChatId())) {
            return;
        }

        reactionService.setReaction(
                message,
                user,
                message.getChatId(),
                request.getReaction()
        );
    }

    @MessageMapping("/delete-reaction")
    public void deleteReaction(
            @Payload
            DeleteReactionRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        User user = userService.getById(userId);
        UserMessageReaction reaction = reactionService.getById(request.getReactionId());

        Chat chat = chatService.getById(reaction);

        if (!Objects.equals(reaction.getUserId(), user.getId())) {
            return;
        }

        reactionService.deleteReaction(
                reaction,
                chat
        );
    }
}