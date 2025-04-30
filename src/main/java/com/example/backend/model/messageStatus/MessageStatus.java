package com.example.backend.model.messageStatus;

import com.example.backend.model.BaseMongoEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Document(collection = "message_status")
public class MessageStatus extends BaseMongoEntity {
    private String userId;
    private String chatId;
    private String messageId;
    private Boolean isRead;
    private Boolean isDeleted;

    public MessageStatus() {
        super();
    }

    public MessageStatus(
            String userId,
            String chatId,
            String messageId
    ) {
        super();
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.isRead = false;
        this.isDeleted = false;
    }
}
