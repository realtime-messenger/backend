package com.example.backend.DTO.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserTypingEvent implements IEvent {
    protected EventType type = EventType.UserTyping;
    private long userId;
    private long chatId;
}
