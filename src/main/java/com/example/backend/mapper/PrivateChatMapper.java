package com.example.backend.mapper;

import com.example.backend.DTO.response.PrivateChatResponse;
import com.example.backend.model.chat.PrivateChat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface PrivateChatMapper {

    @Mapping(target = "id", source = "chat.id")
    PrivateChatResponse toChatResponse (PrivateChat chat);

}