package com.example.backend.service;

import com.example.backend.DTO.response.ChatExtendedResponse;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.repository.ChatRepository;
import com.example.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    private final ChatMapper chatMapper;
    private final MessageService messageService;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, ChatMapper chatMapper, MessageService messageService) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.chatMapper = chatMapper;
        this.messageService = messageService;
    }

    public Collection<ChatExtendedResponse> getUserChats (long userId) {

        List<Chat> userChats = chatRepository.findUserChats(userId);

        List<ChatExtendedResponse> result = new ArrayList<>();

        for (Chat userChat : userChats) {

            Message lastMessage = messageRepository.findFirstByChatIdOrderByIdDesc(
                    userChat.getId()
            );

            ChatExtendedResponse tempResponse = chatMapper.toChatExtendedResponse(userChat);

            tempResponse.setLastMessage(
                    messageService.getMessageExtended(userId, lastMessage)
            );

            result.add(tempResponse);
        }

        return result;
    }

}
