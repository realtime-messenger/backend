package com.example.backend.model.messageContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageContent {
    private MessageContentType type;
    private String url;

    public static MessageContent videoContent(
            String url
    ) {
        return new MessageContent(
                MessageContentType.VIDEO,
                url
        );
    }

    public static MessageContent photoContent(
            String url
    ) {
        return new MessageContent(
                MessageContentType.PHOTO,
                url
        );
    }
}
