package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class ChatNotFoundException extends BaseHttpException {
    public ChatNotFoundException(
    ) {
        super(
                "Chat not found",
                HttpStatus.NOT_FOUND
        );
    }
}
