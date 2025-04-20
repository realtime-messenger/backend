package com.example.backend.service;

import com.example.backend.exceptions.UserAlreadyExistException;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.model.user.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User save(User user) {
        return repository.save(user);
    }


    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(
                        UserNotFoundException::new
                );
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        UserNotFoundException::new
                );

    }

    public User getCurrentUser() {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return getById(id);
    }

    boolean existsByUsername(String username) {
        return this.repository.existsByUsername(username);
    }

}