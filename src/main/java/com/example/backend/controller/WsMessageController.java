package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class WsMessageController {
    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;


    @Autowired
    public WsMessageController(ChatService chatService, MessageService messageService, UserService userService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @MessageMapping("/send-message-chat")
    public void sendMessageChat(
            @Payload
            SendChatMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        if (request.getText().isEmpty()) {
            return;
        }

        if (request.getText().length() > 200) {
            return;
        }

        User user = userService.getById(userId);

        Chat chat = chatService.getById(request.getChatId());

        messageService.sendMessageChat(
                user,
                chat,
                request.getText()
        );
    }

    @MessageMapping("/send-message-private")
    public void sendMessagePrivate(
            @Payload
            SendPrivateMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

        if (request.getText().isEmpty()) {
            return;
        }

        if (request.getText().length() > 200) {
            return;
        }

        if (request.getUserId() == userId) {
            return;
        }

        User firstUser = userService.getById(userId);
        User secondUser = userService.getById(request.getUserId());

        messageService.sendMessagePrivate(
                firstUser,
                secondUser,
                request.getText()
        );
    }

    @MessageMapping("/delete-message")
    public void deleteMessage(
            @Payload
            DeleteMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {

        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
        User user = userService.getById(userId);

        Message message = messageService.getById(request.getMessageId());

        if (request.isGlobal() && !Objects.equals(message.getUserId(), user.getId())) {
            return;
        }

        Chat chat = chatService.getById(
                message.getChatId()
        );
        

        if (request.isGlobal()) {
            messageService.deleteMessage(message, chat);
        }
        else {
            messageService.deleteMessage(message, user);
        }
    }

    @MessageMapping("/read-message")
    public void readMessage(
            @Payload
            ReadMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();
        // TODO
        // проверять, может ли пользователь читать это сообщение, проверять что это не его собственное сообщение

        // при прочтении сообщения отсылать ивент хозяину сообщения о том что его сообщение прочтено
        // и клиентам этого userId о том что это сообщение прочтено.
    }
}