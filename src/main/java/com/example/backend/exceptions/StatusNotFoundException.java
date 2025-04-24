package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class StatusNotFoundException extends BaseHttpException {
    public StatusNotFoundException(
    ) {
        super(
                "Status not found",
                HttpStatus.NOT_FOUND
        );
    }
}
