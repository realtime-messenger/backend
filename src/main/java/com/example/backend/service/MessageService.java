package com.example.backend.service;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.exceptions.ChatNotFoundException;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.mapper.ReactionsMapper;
import com.example.backend.model.message.Message;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MessageService {


    private final MessageMapper messageMapper;
    private final ReactionsMapper reactionsMapper;

    private final MessageRepository messageRepository;
    private final UserChatRepository userChatRepository;
    private final StatusRepository statusRepository;
    private final ReactionRepository reactionRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, ReactionsMapper reactionsMapper, UserChatRepository userChatRepository, StatusRepository statusRepository, ReactionRepository reactionRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.reactionsMapper = reactionsMapper;
        this.userChatRepository = userChatRepository;
        this.statusRepository = statusRepository;
        this.reactionRepository = reactionRepository;
    }

    public MessageExtendedResponse getMessageExtended (long userId, Message message) {
        UserMessageStatus status = statusRepository.findMessageStatusByUserIdAndMessageId(
                userId,
                message.getId()
        );

        List<UserMessageReaction> reactions = reactionRepository.findUserMessageReactionsByMessageId(
                message.getId()
        );

        MessageExtendedResponse tempMessage = messageMapper.toMessageResponseExtended(
                message,
                status
        );

        tempMessage.setReactions(
                reactionsMapper.toReactionsResponseList(reactions)
        );

        return tempMessage;
    }

    public Collection<MessageExtendedResponse> getUserMessages (
            long userId,
            long chatId,
            int skip,
            int limit
    ) {
        if (!userChatRepository.existsUserChatByUserIdAndChatId(userId, chatId)
        ) {
            throw new ChatNotFoundException();
        }

        List<Message> messages = messageRepository.findMessagesByChatId(
                chatId,
                skip,
                limit
        );

        List<MessageExtendedResponse> result = new ArrayList<>();

        for (Message message : messages) {
            result.add(
                    getMessageExtended(userId, message)
            );
        }

        return result;

    }

}
