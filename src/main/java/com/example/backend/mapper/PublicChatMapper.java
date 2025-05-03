package com.example.backend.mapper;

import com.example.backend.DTO.response.PublicChatResponse;
import com.example.backend.model.chat.PublicChat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface PublicChatMapper {

    // TODO
    PublicChatResponse toChatResponse (PublicChat chat);
}