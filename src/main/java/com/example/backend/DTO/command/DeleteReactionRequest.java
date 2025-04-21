package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReactionRequest {

    private long userMessageReactionId;

    public DeleteReactionRequest() {
    }
}
