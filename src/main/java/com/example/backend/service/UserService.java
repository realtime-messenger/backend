package com.example.backend.service;

import com.example.backend.DTO.REST.UserResponse;
import com.example.backend.exceptions.UserAlreadyExistException;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.user.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public User save(User user) {
        return userRepository.save(user);
    }


    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        UserNotFoundException::new
                );
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        UserNotFoundException::new
                );

    }

    public User getCurrentUser() {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return getById(id);
    }


    public List<UserResponse> getUsersAlike(
            String query
    ) {
        return userMapper.toUserResponseList(userRepository.findFuzzy(query));
    }

    boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }
}