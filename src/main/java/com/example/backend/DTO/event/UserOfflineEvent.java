package com.example.backend.DTO.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserOfflineEvent implements IEvent {
    protected EventType type = EventType.UserOffline;
    private long userId;
}
