package com.example.backend.service.business;

import com.example.backend.DTO.response.UserResponse;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.crud.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserCrudService userCrudService;

    @Autowired
    public UserService(UserMapper userMapper, UserCrudService userCrudService) {
        this.userMapper = userMapper;
        this.userCrudService = userCrudService;
    }

    public List<UserResponse> getUsersFuzzy (
            String query,
            Long userId
    ) {
        return userMapper.toUserResponseList(
                userCrudService.getUsersAlike(query, userId)
        );
    }


    public UserResponse getUserById (
            Long userId
    ) {
        return userMapper.toUserResponse(
                userCrudService.getById(userId).orElseThrow(
                    UserNotFoundException::new
                )
        );
    }
}
