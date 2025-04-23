package com.example.backend.service;

import com.example.backend.model.message.Message;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public List<UserMessageReaction> getReactions (Message message) {
        List<UserMessageReaction> reactions = reactionRepository.findUserMessageReactionsByMessageId(
                message.getId()
        );
        return reactions;
    }

}
