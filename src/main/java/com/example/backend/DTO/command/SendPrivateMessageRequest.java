package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendPrivateMessageRequest {

    private long userId;
    private String text;

    public SendPrivateMessageRequest() {
    }
}
