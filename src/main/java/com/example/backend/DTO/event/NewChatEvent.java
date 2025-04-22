package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ChatResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewChatEvent implements IEvent {
    protected EventType type = EventType.NewChat;
    private ChatResponse chat;

    public NewChatEvent (ChatResponse chat) {
        this.chat = chat;
    }
}
