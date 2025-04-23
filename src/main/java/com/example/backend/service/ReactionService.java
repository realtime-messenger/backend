package com.example.backend.service;

import com.example.backend.DTO.event.DeleteReactionEvent;
import com.example.backend.DTO.event.NewReactionEvent;
import com.example.backend.exceptions.ReactionNotFoundException;
import com.example.backend.mapper.ReactionsMapper;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final ReactionsMapper reactionsMapper;
    private final ChatService chatService;
    private final EventProducerService eventProducerService;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository, ReactionsMapper reactionsMapper, ChatService chatService, EventProducerService eventProducerService) {
        this.reactionRepository = reactionRepository;
        this.reactionsMapper = reactionsMapper;
        this.chatService = chatService;
        this.eventProducerService = eventProducerService;
    }

    public UserMessageReaction createReaction(
            Message message,
            User user,
            String reaction
    ) {
        UserMessageReaction newReaction = new UserMessageReaction(
                message,
                user,
                reaction
        );

        return reactionRepository.save(newReaction);
    }

    public List<UserMessageReaction> getReactions (Message message) {
        List<UserMessageReaction> reactions = reactionRepository.findUserMessageReactionsByMessageId(
                message.getId()
        );
        return reactions;
    }

    public UserMessageReaction getById(Long id) {
        return reactionRepository.findById(id)
                .orElseThrow(
                        ReactionNotFoundException::new
                );

    }

    public void setReaction (
            Message message,
            User user,
            long chatId,
            String reaction
    ) {
        UserMessageReaction newReaction = createReaction(message, user, reaction);

        var response = reactionsMapper.toReactionsResponse(newReaction);
        NewReactionEvent event = new NewReactionEvent(response);

        List<User> chatParticipants = chatService.getChatParticipants(chatId);

        chatParticipants.forEach(
                (User chatParticipant) -> {
                    eventProducerService.produceEventToUser(chatParticipant, event);
                }
        );
    }

    public void deleteReaction (
            UserMessageReaction reaction,
            Chat chat
    ) {
        var response = reactionsMapper.toReactionsResponse(reaction);

        reactionRepository.delete(reaction);
        DeleteReactionEvent event = new DeleteReactionEvent(response);

        List<User> chatParticipants = chatService.getChatParticipants(chat.getId());

        chatParticipants.forEach(
                (User chatParticipant) -> {
                    eventProducerService.produceEventToUser(chatParticipant, event);
                }
        );
    }



}
