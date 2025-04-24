package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypingRequest {

    private long chatId;

    public TypingRequest() {
    }
}
