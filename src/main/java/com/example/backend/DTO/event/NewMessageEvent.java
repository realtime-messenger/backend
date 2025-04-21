package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ChatResponse;
import com.example.backend.DTO.response.MessageResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewMessageEvent extends BaseEvent {
    protected EventType type = EventType.NewMessage;
    private MessageResponse message;
}
