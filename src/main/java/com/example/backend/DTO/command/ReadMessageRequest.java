package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadMessageRequest {

    private long messageId;

    public ReadMessageRequest() {
    }
}
