package com.example.backend.DTO.STOMP;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {

    private long chatId;
    private String text;

    public SendMessageRequest() {
    }
}
