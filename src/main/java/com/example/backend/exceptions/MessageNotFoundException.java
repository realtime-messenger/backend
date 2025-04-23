package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends BaseHttpException {
    public MessageNotFoundException(
    ) {
        super(
                "Message not found",
                HttpStatus.NOT_FOUND
        );
    }
}
