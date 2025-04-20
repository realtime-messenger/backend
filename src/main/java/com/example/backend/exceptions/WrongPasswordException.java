package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends BaseHttpException {
    public WrongPasswordException(
    ) {
        super(
                "Password incorrect",
                HttpStatus.FORBIDDEN
        );
    }
}
