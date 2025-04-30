package com.example.backend.service.business;

import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.chat.PrivateChat;
import com.example.backend.model.chat.PublicChat;
import com.example.backend.service.crud.ChatCrudService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatCrudService chatCrudService;

    @Autowired
    public ChatService(ChatCrudService chatCrudService) {
        this.chatCrudService = chatCrudService;
    }

    public void createPublicChat(
            String title,
            List<String> userId
    ) {
        PublicChat chat = new PublicChat(title, userId);
        chatCrudService.save(chat);

        //TODO
        //отослать ивент всем причастным
    }

    public void createPrivateChat(
            String firstUserId,
            String secondUserId
    ) {

        if (chatCrudService.privateChatExists(
                firstUserId,
                secondUserId)
        ) {
            return;
        }

        PrivateChat chat = new PrivateChat(firstUserId, secondUserId);

        chatCrudService.save(chat);

        //TODO
        //отослать ивент всем причастным
    }

    public List<BaseChat> getUserChats (
            @NotNull String userId
    ) {
        return chatCrudService.getChatsByUserId(userId);
    }

}
