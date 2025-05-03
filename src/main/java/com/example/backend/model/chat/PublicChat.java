package com.example.backend.model.chat;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chat")
public class PublicChat extends BaseChat {
    private final ChatType type = ChatType.PUBLIC;
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
