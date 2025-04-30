package com.example.backend.model.chat;

import com.example.backend.model.BaseMongoEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chat")
public class PublicChat extends BaseChat {
    private ChatType type = ChatType.PUBLIC;
    private String title;

    public PublicChat(
            String title,
            List<String> userId
    ) {
        super();
        this.title = title;
        this.userId = userId;
    }
}
