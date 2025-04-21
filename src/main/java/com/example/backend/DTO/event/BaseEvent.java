package com.example.backend.DTO.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BaseEvent {
    protected EventType type;
}
