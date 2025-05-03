package com.example.backend.service.crud;

import com.example.backend.exceptions.UserAlreadyExistException;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.model.user.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.crud.Interface.IDataAccessible;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudService implements IDataAccessible<User, Long> {

    private final UserRepository userRepository;
    private final ChatCrudService chatCrudService;

    @Autowired
    public UserCrudService(UserRepository userRepository, ChatCrudService chatCrudService) {
        this.userRepository = userRepository;
        this.chatCrudService = chatCrudService;
    }

    public User save(User entity) {
        return userRepository.save(entity);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return userRepository.existsById(id);
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

    public List<User> getUsersAlike(
            String query,
            @NotNull Long userId
    ) {

        List<User> users = userRepository.findFuzzy(query);

        users.removeIf(user -> (
                user.getId() == userId ||
                chatCrudService.getPrivateChatOfUsers(user.getId().toString(),userId.toString()).isPresent()
        ));

        return users;
    }

}