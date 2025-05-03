package com.example.backend.model.chat;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "chat")
public class PrivateChat extends BaseChat {
    private ChatType type = ChatType.PRIVATE;

    public PrivateChat (
            List<String> userId
    ) {
        super(userId);
    }

    public PrivateChat(
            String firstUserId,
            String secondUserId
    ){
        super(List.of(firstUserId, secondUserId));
    }
}