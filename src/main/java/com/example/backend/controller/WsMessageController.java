package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.service.business.MessageService;
import com.example.backend.service.crud.ChatCrudService;
import com.example.backend.service.crud.MessageCrudService;
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
    private final UserCrudService userCrudService;
    private final MessageCrudService messageCrudService;
    private final ChatCrudService chatCrudService;

    @Autowired
    public WsMessageController(MessageService messageService, UserCrudService userCrudService, MessageCrudService messageCrudService, ChatCrudService chatCrudService) {
        this.messageService = messageService;
        this.userCrudService = userCrudService;
        this.messageCrudService = messageCrudService;
        this.chatCrudService = chatCrudService;
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

    @MessageMapping("/delete-message")
    public void deleteMessage(
            @Payload
            DeleteMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());
        User user = userCrudService.getById(userId).orElseThrow();

        Message message = messageCrudService.getById(request.getMessageId()).orElseThrow();

        if (request.isGlobal() && !Objects.equals(
                message.getUserId(),
                String.valueOf(user.getId())
            )
        ) {
            return;
        }

        if (request.isGlobal()) {
            messageService.deleteMessage(message);
        }
        else {
            messageService.deleteMessage(message, user);
        }
    }
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