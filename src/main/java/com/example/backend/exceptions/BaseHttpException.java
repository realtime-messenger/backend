package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class BaseHttpException extends RuntimeException {
    private final HttpStatus status;

    public BaseHttpException(
            String message,
            HttpStatus status
    ) {
        super(message);
        this.status = status;

    }

    public HttpStatus getStatusCode () {
        return this.status;
    }

}
