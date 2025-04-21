package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartChatRequest {

    private long userId;

    public StartChatRequest() {
    }
}
