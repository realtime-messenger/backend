package com.example.backend.model.chat;

import com.example.backend.model.BaseMongoEntity;
import com.example.backend.model.message.BaseMessage;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")
public class BaseChat extends BaseMongoEntity {
    protected List<String> userId;
    protected BaseMessage lastMessage;

    public BaseChat (
            List<String> userId
    ) {
        super();
        this.userId = userId;
    }
}