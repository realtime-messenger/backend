package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.model.user.User;
import com.example.backend.service.business.MessageService;
import com.example.backend.service.crud.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class WsMessageController {
    private final MessageService messageService;

    @Autowired
    public WsMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

//
//    @MessageMapping("/send-message-chat")
//    public void sendMessageChat(
//            @Payload
//            SendChatMessageRequest request,
//            SimpMessageHeaderAccessor headerAccessor
//
//    ) throws Exception {
//        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
//
//        if (request.getText().isEmpty()) {
//            return;
//        }
//
//        if (request.getText().length() > 200) {
//            return;
//        }
//
//        User user = userCrudService.getById(userId);
//
//        Chat chat = chatCrudService.getById(request.getChatId());
//
//        messageCrudService.sendMessageChat(
//                user,
//                chat,
//                request.getText()
//        );
//    }
//
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

        messageService.sendPrivateMessage(
                String.valueOf(userId),
                String.valueOf(request.getUserId()),
                request.getText(),
                null,
                null
        );
    }
//
//    @MessageMapping("/delete-message")
//    public void deleteMessage(
//            @Payload
//            DeleteMessageRequest request,
//            SimpMessageHeaderAccessor headerAccessor
//
//    ) throws Exception {
//
//        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
//        User user = userCrudService.getById(userId);
//
//        Message message = messageCrudService.getById(request.getMessageId());
//
//        if (request.isGlobal() && !Objects.equals(message.getUserId(), user.getId())) {
//            return;
//        }
//
//        Chat chat = chatCrudService.getById(
//                message.getChatId()
//        );
//
//
//        if (request.isGlobal()) {
//            messageCrudService.deleteMessage(message, chat);
//        }
//        else {
//            messageCrudService.deleteMessage(message, user);
//        }
//    }
//
//    @MessageMapping("/read-message")
//    public void readMessage(
//            @Payload
//            ReadMessageRequest request,
//            SimpMessageHeaderAccessor headerAccessor
//
//    ) throws Exception {
//        String userId = headerAccessor.getSessionAttributes().get("userId").toString();
//        // TODO
//        // проверять, может ли пользователь читать это сообщение, проверять что это не его собственное сообщение
//
//        // при прочтении сообщения отсылать ивент хозяину сообщения о том что его сообщение прочтено
//        // и клиентам этого userId о том что это сообщение прочтено.
//    }
}