package com.example.backend.mapper;

import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.model.message.Message;
import com.example.backend.model.messageStatus.MessageStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MessageMapper {

    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "dateCreated", source = "message.dateCreated")
    @Mapping(target = "userId", source = "message.userId")
    @Mapping(target = "chatId", source = "message.chatId")
    MessageExtendedResponse toMessageResponseExtended(
            Message message,
            MessageStatus userMessageStatus
    );
}