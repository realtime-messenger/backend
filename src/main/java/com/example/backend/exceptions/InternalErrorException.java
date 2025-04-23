package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends BaseHttpException {
    public InternalErrorException(
    ) {
        super(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
