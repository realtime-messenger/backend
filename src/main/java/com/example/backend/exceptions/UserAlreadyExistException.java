package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends BaseHttpException {
    public UserAlreadyExistException(
    ) {
        super(
                "User with this username already exists",
                HttpStatus.CONFLICT
        );
    }
}
