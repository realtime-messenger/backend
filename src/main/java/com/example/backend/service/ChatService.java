package com.example.backend.service;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.exceptions.ForeignChatInterlocutorException;
import com.example.backend.exceptions.PublicChatInterlocutorException;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.chat.ChatType;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import com.example.backend.repository.ChatRepository;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.UserChatRepository;
import com.example.backend.repository.UserRepository;
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
    private final UserMapper userMapper;
    private final UserChatRepository userChatRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, ChatMapper chatMapper, MessageService messageService, UserMapper userMapper, UserChatRepository userChatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.chatMapper = chatMapper;
        this.messageService = messageService;
        this.userMapper = userMapper;
        this.userChatRepository = userChatRepository;
        this.userRepository = userRepository;
    }

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }


    public User getInterlocutor(long userId, long chatId) {
        List<UserChat> userChats = userChatRepository.getUserChatsByChatId(chatId);

        if (userChats.size() != 2) {
            throw new PublicChatInterlocutorException();
        }

        if (userChats.getFirst().getUserId() == userId) {
            long interlocutorUserId = userChats.getLast().getUserId();
            return userRepository.getUserById(
                interlocutorUserId
            );
        }
        else if (userChats.getLast().getUserId() == userId) {
            long interlocutorUserId = userChats.getFirst().getUserId();
            return userRepository.getUserById(
                    interlocutorUserId
            );
        }
        else {
            throw new ForeignChatInterlocutorException();
        }
    }

    public Collection<ChatResponse> getUserChats (long userId) {
        List<Chat> userChats = chatRepository.findUserChats(userId);

        List<ChatResponse> result = new ArrayList<>();

        for (Chat userChat : userChats) {
            ChatResponse tempResponse = chatMapper.toChatResponse(userChat, null);
            if (userChat.getChatType() == ChatType.PRIVATE) {
                User interlocutor = getInterlocutor(
                        userId,
                        userChat.getId()
                );
                tempResponse.setInterlocutor(
                        userMapper.toUserResponse(
                                interlocutor
                        )
                );
            }

            result.add(tempResponse);
        }

        return result;
    }

    public Chat getUsersPrivateChat (long firstUserId, long secondUserId) {
        Chat result = userChatRepository.getUsersPrivateChat(firstUserId, secondUserId);
        return result;
    }

    public Chat startChat(User firstUser, User secondUser) {
        Chat newChat = new Chat(ChatType.PRIVATE, "");
        save(newChat);

        UserChat firstUserParticipation = new UserChat(
                newChat,
                firstUser
        );
        UserChat secondUserParticipation = new UserChat(
                newChat,
                secondUser
        );

        userChatRepository.save(firstUserParticipation);
        userChatRepository.save(secondUserParticipation);


        return newChat;
    }

}
