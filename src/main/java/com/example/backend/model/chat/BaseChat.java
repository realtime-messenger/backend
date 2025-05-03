package com.example.backend.model.chat;

import com.example.backend.model.BaseMongoEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "chat")
public class BaseChat extends BaseMongoEntity {
    protected List<String> userId;

    public BaseChat (
            List<String> userId
    ) {
        super();
        this.userId = userId;
    }
}