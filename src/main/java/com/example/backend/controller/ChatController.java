package com.example.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chat")
@Tag(name = "Чаты")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    @Operation(summary = "Получить чаты пользователя")
    @GetMapping("")
    public ResponseEntity<String> registration(
//            @RequestHeader(name = "Authorization", required = false)
//            String apiKey
    )  {
        System.out.println("AAAAAAAAAAAAAAa");

        return ResponseEntity.ok("ASDAs");
    }
}
