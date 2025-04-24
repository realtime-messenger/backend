package com.example.backend.controller;

import com.example.backend.DTO.response.IdResponse;
import com.example.backend.DTO.response.UserResponse;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Пользователи")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserResponse> getUsersFuzzy (
            @RequestParam("query")
            @Nullable
            String query
    ) {
        // TODO
        // не показывать конкретно этого пользователя
        // не показыавть пользователей с которыми есть приватный чат
       return userService.getUsersAlike(query);
    }

    @Deprecated
    @GetMapping("/myId")
    public IdResponse getMyUserId () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = Long.parseLong(authentication.getName());
        return new IdResponse(id);
    }
}
