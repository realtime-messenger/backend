package com.example.backend.model.chat;

import com.example.backend.model.BaseMongoEntity;
import com.example.backend.model.message.PrivateMessage;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "chat")
public class PrivateChat extends BaseChat {
    private ChatType type = ChatType.PRIVATE;

    public PrivateChat (
            List<String> userId,
            PrivateMessage lastMessage
    ) {
        super(userId);
        this.lastMessage = lastMessage;
    }

    public PrivateChat(
            String firstUserId,
            String secondUserId
    ){
        super(List.of(firstUserId, secondUserId));
    }
}