package com.example.backend.service;

import com.example.backend.DTO.event.NewChatEvent;
import com.example.backend.DTO.event.NewMessageEvent;
import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.exceptions.ChatNotFoundException;
import com.example.backend.exceptions.InternalErrorException;
import com.example.backend.mapper.ChatMapper;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.mapper.ReactionsMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {


    private final MessageMapper messageMapper;
    private final ChatMapper chatMapper;

    private final MessageRepository messageRepository;

    private final ChatService chatService;
    private final UserChatService userChatService;
    private final EventProducerService eventProducerService;
    private final StatusService statusService;
    private final ReactionService reactionService;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, ChatMapper chatMapper, ChatService chatService, UserChatService userChatService, EventProducerService eventProducerService, StatusService statusService, ReactionService reactionService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.chatMapper = chatMapper;
        this.chatService = chatService;
        this.userChatService = userChatService;
        this.eventProducerService = eventProducerService;
        this.statusService = statusService;
        this.reactionService = reactionService;
    }

    // Возвращает DTO сообщения с реакциями и статусом для конкретного пользователя
    public MessageExtendedResponse getMessageExtended (User user, Message message) {
        UserMessageStatus status = statusService.getStatus(
                user,
                message
        ).orElseThrow(InternalErrorException::new);

        List<UserMessageReaction> reactions = reactionService.getReactions(message);

        return messageMapper.toMessageResponseExtended(
                message,
                status,
                reactions
        );
    }

    // Возвращает список DTO сообщений из чата для конкретного пользователя
    public List<MessageExtendedResponse> getUserMessages (
            User user,
            long chatId,
            int skip,
            int limit
    ) {
        if (!userChatService.userBelongsToChat(user.getId(), chatId)) {
            throw new ChatNotFoundException();
        }

        List<Message> messages = messageRepository.findMessagesByChatId(
                chatId,
                skip,
                limit
        );

        List<MessageExtendedResponse> result = new ArrayList<>();

        for (Message message : messages) {
            result.add(getMessageExtended(user, message));
        }

        return result;
    }

    // Возвращает DTO сообщения по одному из каждого чата в котором состоит пользователь
    public List<MessageExtendedResponse> getLastMessages (
            User user
    ) {
        List<UserChat> userChats = userChatService.getUserChats(user);

        List<MessageExtendedResponse> result = new ArrayList<>();

        for (UserChat userChat : userChats) {
            Optional<Message> message = messageRepository.findMessageByChatId(
                    userChat.getChatId(),
                    0,
                    1
            );

            if (message.isEmpty()) {
                continue;
            }

            result.add(
                    getMessageExtended(user, message.get())
            );
        }

        return result;
    }

    // Только создаёт сообщение
    public Message createMessage(
            User sender,
            Chat chat,
            String text
    ) {
        Message newMessage = new Message(
                chat,
                sender,
                text
        );

        return messageRepository.save(newMessage);
    }

    // Создаёт статусы для всех пользователей которые могут иметь доступ к этому сообщению
    public List<UserMessageStatus> createMessageStatuses (
            Message message,
            Chat chat
    ) {
        List<User> participants = chatService.getChatParticipants(chat);
        return statusService.createStatus(participants, message);
    }

    // Отправляет сообщение пользователю в личный чат. Если чата не существует - создаёт.
    public void sendMessagePrivate (
            User sender,
            User getter,
            String text
    ) {
        Optional<Chat> commonChat = userChatService.getUsersPrivateChat(
                sender.getId(),
                getter.getId()
        );

        Chat chat;

        if (commonChat.isEmpty()) {
            chat = chatService.startChat(
                    sender,
                    getter
            );

            eventProducerService.produceEventToUser(
                    sender,
                    new NewChatEvent(chatMapper.toChatResponse(chat, getter))
            );

            eventProducerService.produceEventToUser(
                    getter,
                    new NewChatEvent(chatMapper.toChatResponse(chat, sender))
            );
        }
        else {
            chat = commonChat.get();
        }

        Message message = createMessage(
                sender,
                chat,
                text
        );

        createMessageStatuses(
                message,
                chat
        );

        var response = messageMapper.toMessageResponseExtended(message, false, new ArrayList<>());

        NewMessageEvent event = new NewMessageEvent(response);
        eventProducerService.produceEventToUser(sender, event);
        eventProducerService.produceEventToUser(getter, event);
    }

    public void sendMessageChat (
            User user,
            Chat chat,
            String text
    ) {
        if (!userChatService.userBelongsToChat(user.getId(), chat.getId())) {
            return;
        }

        Message message = createMessage(
                user,
                chat,
                text
        );

        createMessageStatuses(
                message,
                chat
        );

        var response = messageMapper.toMessageResponseExtended(message, false, new ArrayList<>());
        NewMessageEvent event = new NewMessageEvent(response);

        List<User> chatParticipants = chatService.getChatParticipants(chat);

        chatParticipants.forEach(
                (User chatParticipant) -> {
                    eventProducerService.produceEventToUser(chatParticipant, event);
                }
        );
    }
}
