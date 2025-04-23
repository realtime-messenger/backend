package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class ReactionNotFoundException extends BaseHttpException {
    public ReactionNotFoundException(
    ) {
        super(
                "Reaction not found",
                HttpStatus.NOT_FOUND
        );
    }
}
