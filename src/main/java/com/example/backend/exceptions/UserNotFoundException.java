package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseHttpException {
    public UserNotFoundException(
    ) {
        super(
                "User not found",
                HttpStatus.NOT_FOUND
        );
    }
}
