package com.example.backend.DTO.event;

import com.example.backend.DTO.response.ReactionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteReactionEvent implements IEvent {
    protected EventType type = EventType.DeleteReaction;
    private ReactionResponse reaction;

    public DeleteReactionEvent(
            ReactionResponse reaction
    ) {
        this.reaction = reaction;
    }
}
