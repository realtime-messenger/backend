package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ChatResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewChatEvent extends BaseEvent {
    protected EventType type = EventType.NewChat;
    private ChatResponse chat;
}
