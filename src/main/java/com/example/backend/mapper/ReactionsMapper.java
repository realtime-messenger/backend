package com.example.backend.mapper;

import com.example.backend.DTO.MessageResponse;
import com.example.backend.DTO.MessageResponseExtended;
import com.example.backend.DTO.ReactionResponse;
import com.example.backend.model.message.Message;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ReactionsMapper {

    ReactionResponse toReactionsResponse(UserMessageReaction userMessageReaction); //map User to UserResponse
    List<ReactionResponse> toReactionsResponseList(List<UserMessageReaction> userMessageReactions); //map list of User to list of UserResponse
}