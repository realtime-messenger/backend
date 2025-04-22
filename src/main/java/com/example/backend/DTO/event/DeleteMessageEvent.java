package com.example.backend.DTO.event;

import com.example.backend.DTO.response.MessageResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteMessageEvent implements IEvent {
    protected EventType type = EventType.DeleteMessage;
    private long chatId;
    private long messageId;
}
