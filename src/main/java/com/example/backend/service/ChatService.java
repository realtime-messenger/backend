package com.example.backend.service;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.exceptions.ForeignChatInterlocutorException;
import com.example.backend.exceptions.PublicChatInterlocutorException;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.chat.ChatType;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatMapper chatMapper;
    private final UserMapper userMapper;


    private final UserService userService;
    private final UserChatService userChatService;

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, ChatMapper chatMapper, UserMapper userMapper, UserService userService, UserChatService userChatService) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.userChatService = userChatService;
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public User getInterlocutor(long userId, long chatId) {
        List<UserChat> userChats = userChatService.getUserChatsByChatId(chatId);

        if (userChats.size() != 2) {
            throw new PublicChatInterlocutorException();
        }

        long interlocutorUserId;

        if (userChats.getFirst().getUserId() == userId) {
            interlocutorUserId = userChats.getLast().getUserId();
            return userService.getById(interlocutorUserId);

        }
        else if (userChats.getLast().getUserId() == userId) {
            interlocutorUserId = userChats.getFirst().getUserId();
            return userService.getById(interlocutorUserId);
        }
        else {
            throw new ForeignChatInterlocutorException();
        }
    }

    public List<Chat> getUserChats (User user) {
        return chatRepository.findUserChats(user.getId());
    }

    public List<User> getChatParticipants (Chat chat) {
        return chatRepository.findChatParticipants(chat.getId());
    }

    public List<User> getChatParticipants (long chatId) {
        return chatRepository.findChatParticipants(chatId);
    }

    public List<ChatResponse> getUserChatsResponse (User user) {
        List<Chat> userChats = chatRepository.findUserChats(user.getId());

        List<ChatResponse> result = new ArrayList<>();

        for (Chat chat : userChats) {
            ChatResponse tempResponse = chatMapper.toChatResponse(chat, null);
            if (chat.getChatType() == ChatType.PRIVATE) {
                User interlocutor = getInterlocutor(
                        user.getId(),
                        chat.getId()
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

    public Chat startChat(User firstUser, User secondUser) {
        Chat newChat = createChat(
                new Chat(ChatType.PRIVATE, "")
        );

        userChatService.addToChat(firstUser, newChat);
        userChatService.addToChat(secondUser, newChat);

        return newChat;
    }

    public Chat getById (long chatId) {
        return chatRepository.getChatById(chatId);
    }

    public Chat getById (UserMessageReaction reaction) {
        return chatRepository.getChatByReactionId(reaction.getId());
    }

}
