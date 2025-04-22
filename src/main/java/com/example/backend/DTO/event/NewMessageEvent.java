package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.DTO.response.MessageExtendedResponse;
import com.example.backend.DTO.response.MessageResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewMessageEvent implements IEvent {
    protected EventType type = EventType.NewMessage;
    private MessageExtendedResponse message;

    public NewMessageEvent(
            MessageExtendedResponse message
    ) {
        this.message = message;
    }
}
