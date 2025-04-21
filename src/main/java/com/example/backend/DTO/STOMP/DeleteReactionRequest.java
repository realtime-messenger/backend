package com.example.backend.DTO.STOMP;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReactionRequest {

    private long userMessageReactionId;

    public DeleteReactionRequest() {
    }
}
