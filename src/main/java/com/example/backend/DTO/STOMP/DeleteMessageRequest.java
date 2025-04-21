package com.example.backend.DTO.STOMP;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMessageRequest {

    private long messageId;
    private boolean global;

    public DeleteMessageRequest() {
    }
}
