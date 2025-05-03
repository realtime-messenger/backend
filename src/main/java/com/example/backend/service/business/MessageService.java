package com.example.backend.service.business;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.exceptions.InternalErrorException;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.chat.PrivateChat;
import com.example.backend.model.message.Message;
import com.example.backend.model.messageContent.MessageContent;
import com.example.backend.model.messageStatus.MessageStatus;
import com.example.backend.model.user.User;
import com.example.backend.service.crud.ChatCrudService;
import com.example.backend.service.crud.MessageCrudService;
import com.example.backend.service.crud.MessageStatusCrudService;
import jakarta.annotation.Nullable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final ChatCrudService chatCrudService;
    private final MessageCrudService messageCrudService;
    private final MessageStatusCrudService messageStatusCrudService;
    private final MessageMapper messageMapper;

    public MessageService(ChatCrudService chatCrudService, MessageCrudService messageCrudService, MessageStatusCrudService messageStatusCrudService, MessageMapper messageMapper) {
        this.chatCrudService = chatCrudService;
        this.messageCrudService = messageCrudService;
        this.messageStatusCrudService = messageStatusCrudService;
        this.messageMapper = messageMapper;
    }

    public void sendPrivateMessage(
            String senderId,
            String receiverId,
            @Nullable
            String text,
            @Nullable
            List<String> videoUrls,
            @Nullable
            List<String> photoUrls
    ) {
        PrivateChat chat = chatCrudService.getPrivateChatOfUsers(
                senderId,
                receiverId
        ).orElseGet(
            () -> (PrivateChat) chatCrudService.save(
                new PrivateChat(
                        senderId,
                        receiverId
                )
            )
        );

        List<MessageContent> photo = null;
        List<MessageContent> video = null;

        if (photoUrls != null) {
            photo = new ArrayList<>();
            for (String url : photoUrls) {
                photo.add(
                        MessageContent.photoContent(url)
                );
            }
        }

        if (videoUrls != null) {
            video = new ArrayList<>();
            for (String url : videoUrls) {
                video.add(
                        MessageContent.videoContent(url)
                );
            }
        }


        Message newMessage = Message.privateMessage(
                senderId,
                chat.getId(),
                text,
                photo,
                video,
                null
        );

        messageCrudService.save(newMessage);

        messageStatusCrudService.save(
                new MessageStatus(
                        senderId,
                        chat.getId(),
                        newMessage.getId()
                )
        );
        messageStatusCrudService.save(
                new MessageStatus(
                        receiverId,
                        chat.getId(),
                        newMessage.getId()
                )
        );

        // TODO
        // produce event to users;
    }

    // метод для отправки сообщения в группу

    // метод для чтения сообщений

    public void deleteMessage (Message message) {
        //TODO отправлять ивент по destination /chat

        messageStatusCrudService.markDeleted(message);
    }


    public void deleteMessage (Message message, User user) {
        //TODO отправлять ивент по destination /user

        messageStatusCrudService.markDeleted(message, user);
    }


    public List<MessageExtendedResponse> getChatMessages (
            String userId,
            String chatId,
            int skip,
            int limit
    ) {
        var messages = messageCrudService.getChatMessages(
                String.valueOf(chatId),
                skip,
                limit
        );

        List<MessageExtendedResponse> result = new ArrayList<>();

        for (Message message : messages) {

            MessageStatus status = messageStatusCrudService.getByMessageIdAndUserId(
                    message.getId(),
                    userId
            ).orElseThrow(InternalErrorException::new);

            if (status.getIsDeleted()) {
                continue;
            }

            result.add(
                    messageMapper.toMessageResponseExtended(
                            message,
                            status
                    )
            );
        }

        return result;
    }

    public List<MessageExtendedResponse> getLastMessages (
            String userId
    ) {
        List<MessageStatus> statuses = messageStatusCrudService.getStatusesForLastMessages(userId);

        List<MessageExtendedResponse> result = new ArrayList<>();

        for (MessageStatus status : statuses) {
            Message message = messageCrudService.getById(
                    status.getMessageId()
            ).orElseThrow(InternalErrorException::new);

            result.add(
                messageMapper.toMessageResponseExtended(
                        message,
                        status
                )
            );
        }

        return result;
    }
}
