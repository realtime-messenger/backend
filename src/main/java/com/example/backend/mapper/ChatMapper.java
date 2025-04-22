package com.example.backend.mapper;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ChatMapper {

    @Mapping(target = "id", source = "chat.id")
    @Mapping(target = "dateCreated", source = "chat.dateCreated")
    ChatResponse toChatResponse(Chat chat, User interlocutor);
}