package com.example.backend.DTO.response;

import com.example.backend.model.chat.ChatType;
import com.example.backend.model.message.BaseMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


public class ChatResponse {

    public class BaseChatResponse {
        private String id;
        private String type;

        private List<String> userId;
        private MessageResponse lastMessage;

        private LocalDateTime dateCreated;
    }

    @EqualsAndHashCode(callSuper = true)
    @Getter
    @Setter
    @Data
    @Schema(description = "Ответ личного чата")
    public class PrivateChatResponse extends BaseChatResponse {

    }

    @EqualsAndHashCode(callSuper = true)
    @Getter
    @Setter
    @Data
    @Schema(description = "Ответ беседы")
    public class PublicChatResponse extends BaseChatResponse {
        private String title;
    }

}
