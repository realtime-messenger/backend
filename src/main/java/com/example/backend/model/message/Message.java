package com.example.backend.model.message;

import com.example.backend.model.BaseMongoEntity;
import com.example.backend.model.messageReaction.MessageReaction;
import com.example.backend.model.messageContent.MessageContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "message")
@Getter
@Setter
@AllArgsConstructor
public class Message extends BaseMongoEntity {
    private MessageType type;

    private String userId;
    private String chatId;

    private String text;
    private List<MessageContent> photo;
    private List<MessageContent> video;
    private List<MessageReaction> reaction;

    public Message () {
        super();
    }

    public static Message privateMessage (
            String userId,
            String chatId,
            String text,
            List<MessageContent> photo,
            List<MessageContent> video,
            List<MessageReaction> reaction
    ) {
        return new Message(
                MessageType.PRIVATE,
                userId,
                chatId,
                text,
                photo,
                video,
                reaction
        );
    }

    public static Message publicMessage (
            String userId,
            String chatId,
            String text,
            List<MessageContent> photo,
            List<MessageContent> video,
            List<MessageReaction> reaction
    ) {
        return new Message(
                MessageType.PUBLIC,
                userId,
                chatId,
                text,
                photo,
                video,
                reaction
        );
    }

}
