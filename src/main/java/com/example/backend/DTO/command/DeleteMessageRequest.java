package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMessageRequest {

    private String messageId;
    private boolean global;

    public DeleteMessageRequest() {
    }
}
