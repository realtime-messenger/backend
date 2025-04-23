package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {
    private final ChatService chatService;
    private final MessageService messageService;
    private final ChatMapper chatMapper;
    private final UserService userService;
    private final EventProducerService eventProducerService;
    private final UserChatService userChatService;
    private final ReactionService reactionService;


    @Autowired
    public WebSocketController(ChatService chatService, MessageService messageService, ChatMapper chatMapper, UserService userService, EventProducerService eventProducerService, UserChatService userChatService, ReactionService reactionService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.chatMapper = chatMapper;
        this.userService = userService;
        this.eventProducerService = eventProducerService;
        this.userChatService = userChatService;
        this.reactionService = reactionService;
    }

    @MessageMapping("/start-chat")
    public void startChat(
            @Payload
            StartChatRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // написать сервис проверяющий наличие приватного чата с такими пользователями
        // написать сервис создающий тако й чат
        // отправлять ивент о создании такого чата всем кого он касается

        System.out.println(userId);

        eventProducerService.produceEvent(
                "/topic/broadcast",
                "hi all"
        );
    }

    @MessageMapping("/send-message-chat")
    public void sendMessageChat(
            @Payload
            SendChatMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        long userId = Long.parseLong(headerAccessor.getSessionAttributes().get("userId").toString());

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




        // если пользователь удаляет сообщения неглобально, то нужно пометить его в базе данных как удалённый у него
        // и отослать ивент об удалении сообщения всем клиентам с этим userId

        // если пользователь удаляет сообщение глобально, то нужно проверить может ли он вообще так делать
        // пометить сообщение удалённым у всех и отослать всем ивент об его удалении
    }

    @MessageMapping("/read-message")
    public void readMessage(
            @Payload
            ReadMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // проверять, может ли пользователь читать это сообщение, проверять что это не его собственное сообщение

        // при прочтении сообщения отсылать ивент хозяину сообщения о том что его сообщение прочтено
        // и клиентам этого userId о том что это сообщение прочтено.
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
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // проверять что эта реакция принадлежит этому пользователю

        // удалять реакцию и отсылать ивент всем причастным

    }



}