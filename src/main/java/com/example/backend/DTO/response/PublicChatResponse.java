package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@NoArgsConstructor
@Schema(description = "Ответ беседы")
public class PublicChatResponse extends BaseChatResponse {
    private String title;
}
