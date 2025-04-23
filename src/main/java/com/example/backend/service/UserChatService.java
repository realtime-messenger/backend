package com.example.backend.service;

import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import com.example.backend.repository.UserChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserChatService {

    private final UserChatRepository userChatRepository;

    @Autowired
    public UserChatService(UserChatRepository userChatRepository) {
        this.userChatRepository = userChatRepository;
    }


    public boolean userBelongsToChat(long userId, long chatId) {
        return userChatRepository.existsUserChatByUserIdAndChatId(userId, chatId);
    }

    public List<UserChat> getUserChats (User user) {
        return userChatRepository.getUserChatsByUserId(user.getId());
    }

    public List<UserChat> getUserChats (Chat chat) {
        return userChatRepository.getUserChatsByChatId(chat.getId());
    }

    public List<UserChat> getUserChatsByChatId (Long chatId) {
        return userChatRepository.getUserChatsByChatId(chatId);
    }

    public Optional<Chat> getUsersPrivateChat (long firstUserId, long secondUserId) {
        return userChatRepository.getUsersPrivateChat(firstUserId, secondUserId);
    }

    public UserChat addToChat(User user, Chat chat) {
        UserChat part = new UserChat(
                chat,
                user
        );

        return userChatRepository.save(part);
    }


}
