package com.example.backend.model.message;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "message")
public class PublicMessage extends BaseMessage {
    private final MessageType type = MessageType.PUBLIC;


    public PublicMessage () {
        super();
    }
}


