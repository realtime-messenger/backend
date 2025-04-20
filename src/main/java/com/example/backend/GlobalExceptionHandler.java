package com.example.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusExceptions(ResponseStatusException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(
                "error", ex.getReason()
        );

        errors.put(
                "errorMessage", ex.getMessage()
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleNotReadableMessage(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(
                "error", "Required request body is missing"
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(
                "error", ex.getMessage()
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(
                "error", ex.getMessage()
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(BaseHttpException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(
                "error", ex.getMessage()
        );

        var response = ResponseEntity
                .status(ex.getStatusCode())
                .body(errors);

        return response;
    }
}