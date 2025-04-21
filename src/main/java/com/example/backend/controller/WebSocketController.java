package com.example.backend.controller;

import com.example.backend.DTO.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
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
        System.out.println(request.getUserId());


        template.convertAndSend("/topic/broadcast", "hi all");
        template.convertAndSend("/topic/broadcast", "hi all");

    }

    @MessageMapping("/send-message")
    public void sendMessage(
            @Payload
            SendMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor

    ) throws Exception {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();

        // написать сервис проверяющий может ли пользователь отправлять сообщения в этот чат
        // написать сервис отправляющий сообщение
        // отправлять ивент об отправленном сообщении всем кого он касается
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