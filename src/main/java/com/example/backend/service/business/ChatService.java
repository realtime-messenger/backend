package com.example.backend.service.business;

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
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final ChatCrudService chatCrudService;
    private final UserCrudService userCrudService;
    private final PrivateChatMapper privateChatMapper;
    private final PublicChatMapper publicChatMapper;

    @Autowired
    public ChatService(ChatCrudService chatCrudService, UserCrudService userCrudService, PrivateChatMapper privateChatMapper, PublicChatMapper publicChatMapper) {
        this.chatCrudService = chatCrudService;
        this.userCrudService = userCrudService;
        this.privateChatMapper = privateChatMapper;
        this.publicChatMapper = publicChatMapper;
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

    public List<BaseChatResponse> getUserChats (
            @NotNull Long id
    ) {
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

        return result;
    }

}
