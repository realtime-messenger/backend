package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class PublicChatInterlocutorException extends BaseHttpException {
    public PublicChatInterlocutorException(
    ) {
        super(
                "Can not get interlocutor in public chat",
                HttpStatus.BAD_REQUEST
        );
    }
}
