package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetReactionRequest {

    private long messageId;
    private String reaction;

    public SetReactionRequest() {
    }
}
