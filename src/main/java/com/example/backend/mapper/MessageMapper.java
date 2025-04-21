package com.example.backend.mapper;

import com.example.backend.DTO.response.MessageResponse;
import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.model.message.Message;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MessageMapper {

    MessageResponse toMessageResponse(Message message); //map User to UserResponse


    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "dateCreated", source = "message.dateCreated")
    MessageExtendedResponse toMessageResponseExtended(
            Message message,
            UserMessageStatus userMessageStatus
    );

    List<MessageResponse> toMessagesResponseList(List<Message> messages); //map list of User to list of UserResponse
}