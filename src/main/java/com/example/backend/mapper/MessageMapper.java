package com.example.backend.mapper;

import com.example.backend.DTO.response.MessageResponse;
import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userMessageReaction.UserMessageReaction;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MessageMapper {

    MessageResponse toMessageResponse(Message message);

    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "dateCreated", source = "message.dateCreated")
    @Mapping(target = "user", source = "user")
    MessageExtendedResponse toMessageResponseExtended(
            Message message,
            User user,
            UserMessageStatus userMessageStatus,
            Collection<UserMessageReaction> reactions
    );

    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "dateCreated", source = "message.dateCreated")
    @Mapping(target = "isRead", source = "isRead")
    @Mapping(target = "user", source = "user")
    MessageExtendedResponse toMessageResponseExtended(
            Message message,
            User user,
            boolean isRead,
            Collection<UserMessageReaction> reactions
    );



    List<MessageResponse> toMessagesResponseList(List<Message> messages);
}