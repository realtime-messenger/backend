package com.example.backend.DTO.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class BaseChatResponse {
    protected String id;
    protected String type;

    protected List<String> userId;
    protected MessageResponse lastMessage;

    protected LocalDateTime dateCreated;
}

