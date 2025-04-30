package com.example.backend.model.message;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Document(collection = "message")
public class PublicMessage extends BaseMessage {
    private MessageType type = MessageType.PUBLIC;


    public PublicMessage () {
        super();
    }
}


