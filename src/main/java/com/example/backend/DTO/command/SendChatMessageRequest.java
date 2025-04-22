package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendChatMessageRequest {

    private long chatId;
    private String text;

    public SendChatMessageRequest() {
    }
}
