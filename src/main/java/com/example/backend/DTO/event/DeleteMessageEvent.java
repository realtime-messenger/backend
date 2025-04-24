package com.example.backend.DTO.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteMessageEvent implements IEvent {
    protected EventType type = EventType.DeleteMessage;
    private long messageId;
}
