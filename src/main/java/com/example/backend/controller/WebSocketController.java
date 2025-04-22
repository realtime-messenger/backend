package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import com.example.backend.DTO.event.NewChatEvent;
import com.example.backend.DTO.event.NewMessageEvent;
import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.example.backend.service.ChatService;
import com.example.backend.service.EventProducerService;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
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


    @Autowired
    public WebSocketController(ChatService chatService, MessageService messageService, ChatMapper chatMapper, UserService userService, EventProducerService eventProducerService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.chatMapper = chatMapper;
        this.userService = userService;
        this.eventProducerService = eventProducerService;
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
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // написать сервис проверяющий может ли пользователь отправлять сообщения в этот чат
        // написать сервис отправляющий сообщение
        // отправлять ивент об отправленном сообщении всем кого он касается
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

        if (firstUser == null || secondUser == null) {
            return;
        }

        Chat chat = chatService.getUsersPrivateChat(
                firstUser.getId(),
                secondUser.getId()
        );

        if (chat == null) {
            chat = chatService.startChat(
                    firstUser,
                    secondUser
            );

            eventProducerService.produceEventToUser(
                    firstUser,
                    new NewChatEvent(chatMapper.toChatResponse(chat, firstUser))
            );

            eventProducerService.produceEventToUser(
                    secondUser,
                    new NewChatEvent(chatMapper.toChatResponse(chat, secondUser))
            );
        }

        MessageExtendedResponse response = messageService.sendMessage(
                firstUser,
                chat,
                request.getText()
        );

        NewMessageEvent event = new NewMessageEvent(response);
        eventProducerService.produceEventToUser(firstUser, event);
        eventProducerService.produceEventToUser(secondUser, event);
    }

    @MessageMapping("/delete-message")
    public void deleteMessage(
            @Payload
            DeleteMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

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
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // проверять что пользователь может вообще ставить реакции на это сообщение
        // если может то проверять нет ли реакции этого пользователя на этом сообщении
        // если нет то ставить, если есть то заменять, если есть и уже такая же то игнорировать

        // возможно не проверять наличие текущих реакций

        // отсылать всем причастным ивент о том что на это сообщении появилась реакция
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