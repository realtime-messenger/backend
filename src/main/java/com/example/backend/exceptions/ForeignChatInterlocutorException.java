package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class ForeignChatInterlocutorException extends BaseHttpException {
    public ForeignChatInterlocutorException(
    ) {
        super(
                "Can not get interlocutor in of chat you are not participate",
                HttpStatus.BAD_REQUEST
        );
    }
}
