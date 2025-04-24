package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ReactionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewReactionEvent implements IEvent {
    protected EventType type = EventType.NewReaction;
    private ReactionResponse reaction;

    public NewReactionEvent(
            ReactionResponse reaction
    ) {
        this.reaction = reaction;
    }
}
