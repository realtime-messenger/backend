package com.example.backend.mapper;

import com.example.backend.DTO.REST.ChatResponse;
import com.example.backend.model.chat.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ChatMapper {

    ChatResponse toChatResponse(Chat chat); //map User to UserResponse
    List<ChatResponse> toChatResponseList(List<Chat> chats); //map list of User to list of UserResponse
}