package com.example.backend.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@NoArgsConstructor
@Schema(description = "Ответ личного чата")
public class PrivateChatResponse extends BaseChatResponse {

}
