package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.model.user.User;
import com.example.backend.service.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class WsReactionController {
//    private final MessageCrudService messageCrudService;
//    private final UserCrudService userCrudService;
//    private final UserChatCrudService userChatCrudService;
//    private final ReactionCrudService reactionService;
//    private final ChatCrudService chatCrudService;

//
//    @Autowired
//    public WsReactionController(
//            MessageCrudService messageCrudService,
//            UserCrudService userCrudService,
//            UserChatCrudService userChatCrudService,
//            ReactionCrudService reactionService,
//            ChatCrudService chatCrudService) {
//        this.messageCrudService = messageCrudService;
//        this.userCrudService = userCrudService;
//        this.userChatCrudService = userChatCrudService;
//        this.reactionService = reactionService;
//        this.chatCrudService = chatCrudService;
//    }
//
//
//    @MessageMapping("/set-reaction")
//    public void setReaction(
//            @Payload
//            SetReactionRequest request,
//            SimpMessageHeaderAccessor headerAccessor
//
//    ) throws Exception {
//        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
//
//        User user = userCrudService.getById(userId);
//        Message message = messageCrudService.getById(request.getMessageId());
//
//        if (!userChatCrudService.userBelongsToChat(user.getId(), message.getChatId())) {
//            return;
//        }
//
//        reactionService.setReaction(
//                message,
//                user,
//                message.getChatId(),
//                request.getReaction()
//        );
//    }
//
//    @MessageMapping("/delete-reaction")
//    public void deleteReaction(
//            @Payload
//            DeleteReactionRequest request,
//            SimpMessageHeaderAccessor headerAccessor
//
//    ) throws Exception {
//        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
//
//        User user = userCrudService.getById(userId);
//        UserMessageReaction reaction = reactionService.getById(request.getReactionId());
//
//        Chat chat = chatCrudService.getByReaction(reaction);
//
//        if (!Objects.equals(reaction.getUserId(), user.getId())) {
//            return;
//        }
//
//        reactionService.deleteReaction(
//                reaction,
//                chat
//        );
//    }
}