package com.example.backend.DTO.event;

import com.example.backend.DTO.response.BaseChatResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewChatEvent implements IEvent {
    protected EventType type = EventType.NewChat;
    private BaseChatResponse chat;

    public NewChatEvent (BaseChatResponse chat) {
        this.chat = chat;
    }
}
