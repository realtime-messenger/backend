package com.example.backend.DTO.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StartChatRequest {

    private List<Long> userIds;
    private String title;

    public StartChatRequest() {
    }
}
