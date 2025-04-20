package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class JwtException extends BaseHttpException {
    public JwtException(
    ) {
        super(
                "Your jwt token either malformed or expired",
                HttpStatus.FORBIDDEN
        );
    }
}
