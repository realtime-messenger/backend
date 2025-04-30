package com.example.backend.mapper;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.model.chat.BaseChat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ChatMapper {

    // TODO
    List<ChatResponse> toListChatResponse(List<BaseChat> chat);
}