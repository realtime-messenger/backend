package com.example.backend.service.business;

import com.example.backend.model.message.Message;
import com.example.backend.model.messageReaction.MessageReaction;
import com.example.backend.service.crud.MessageCrudService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    private final MessageCrudService messageCrudService;

    public ReactionService(MessageCrudService messageCrudService) {
        this.messageCrudService = messageCrudService;
    }

    public void setReaction (
            @NotNull String userId,
            @NotNull String messageId,
            @NotNull String reaction
    ) {
        Optional<Message> opt_Message = messageCrudService.getById(messageId);

        if (opt_Message.isEmpty()) {
            return;
        }

        Message message = opt_Message.get();

        List<MessageReaction> reactions = message.getReaction();

        for (MessageReaction existingReaction : reactions) {
            if (existingReaction.getUserId() == userId) {
                reactions.remove(existingReaction);
                // TODO
                // send reaction delete event
                break;
            }
        }

        MessageReaction newReaction = new MessageReaction(
                userId,
                message.getChatId(),
                messageId,
                reaction
        );

        reactions.add(newReaction);

        message.setReaction(reactions);

        messageCrudService.save(message);

        //TODO
        //send event about new reaction to all users
    }

    public void deleteReaction (
            @NotNull String userId,
            @NotNull String messageId
    ) {
        Optional<Message> opt_Message = messageCrudService.getById(messageId);

        if (opt_Message.isEmpty()) {
            return;
        }

        Message message = opt_Message.get();

        List<MessageReaction> reactions = message.getReaction();

        for (MessageReaction existingReaction : reactions) {
            if (existingReaction.getUserId() == userId) {
                reactions.remove(existingReaction);
                // TODO
                // send reaction delete event
                break;
            }
        }

        message.setReaction(reactions);

        messageCrudService.save(message);

        //TODO
        //send event about deleted reaction to all users
    }
}
