package com.example.backend.model.message;

import com.example.backend.model.messageContent.MessageContent;
import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "message")
public class PrivateMessage extends BaseMessage {
    private String receiverUserId;
    private MessageType type = MessageType.PRIVATE;

    public PrivateMessage (
            String chatId,
            String senderUserId,
            String receiverUserId,
            @Nullable String text,
            @Nullable List<MessageContent> photo,
            @Nullable List<MessageContent> video
    ) {
        super();
        this.chatId = chatId;
        this.userId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.text = text;
        this.photo = photo;
        this.video = video;
    }
}

