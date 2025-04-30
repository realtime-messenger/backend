package com.example.backend.model.message;

import com.example.backend.model.BaseMongoEntity;
import com.example.backend.model.messageReaction.MessageReaction;
import com.example.backend.model.messageContent.MessageContent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "message")
@Getter
@Setter
public class BaseMessage extends BaseMongoEntity {
    protected String userId;
    protected String chatId;

    protected String text;
    protected List<MessageContent> photo;
    protected List<MessageContent> video;
    protected List<MessageReaction> reaction;

    public BaseMessage () {
        super();
    }


}
