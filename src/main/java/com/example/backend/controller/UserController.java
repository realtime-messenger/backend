package com.example.backend.controller;

import com.example.backend.DTO.response.UserResponse;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.crud.UserCrudService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserCrudService userCrudService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserCrudService userCrudService, UserMapper userMapper) {
        this.userCrudService = userCrudService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public List<UserResponse> getUsersFuzzy (
            @RequestParam("query")
            @Nullable
            String query
    ) {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

       return userMapper.toUserResponseList(
               userCrudService.getUsersAlike(query, id)
       );
    }

}
