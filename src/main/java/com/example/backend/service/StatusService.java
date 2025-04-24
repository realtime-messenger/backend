package com.example.backend.service;

import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userMessageStatus.UserMessageStatus;
import com.example.backend.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.exceptions.StatusNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public UserMessageStatus save(UserMessageStatus status) {
        return statusRepository.save(status);
    }

    public List<UserMessageStatus> save(List<UserMessageStatus>  statuses) {
        return statusRepository.saveAll(statuses);
    }

    public Optional<UserMessageStatus> getStatus (User user, Message message) {
        Optional<UserMessageStatus> status = statusRepository.findMessageStatusByUserIdAndMessageId(
                user.getId(),
                message.getId()
        );
        return status;
    }

    public UserMessageStatus createStatus (
            User user,
            Message message
    ) {

        UserMessageStatus status = new UserMessageStatus(
                message,
                user
        );

        statusRepository.save(status);

        return status;
    }

    public List<UserMessageStatus> createStatus (
            List<User> users,
            Message message
    ) {
        ArrayList<UserMessageStatus> statuses = new ArrayList<>();

        for (User participant : users) {
            UserMessageStatus status = new UserMessageStatus(
                    message,
                    participant
            );
            statuses.add(status);
        }

        statusRepository.saveAll(statuses);
        return statuses;
    }

    public void MarkDeleted (
            Message message,
            User user
    ) {
        UserMessageStatus status = statusRepository.findMessageStatusByUserIdAndMessageId(
                user.getId(),
                message.getId()
        ).orElseThrow(StatusNotFoundException::new);

        status.setIsDeleted(true);

        save(status);
    }

    public void MarkDeleted (
            Message message
    ) {
        List<UserMessageStatus> statuses = statusRepository.getUserMessageStatusesByMessageId(message.getId());

        for (UserMessageStatus status : statuses) {
            status.setIsDeleted(true);
        }

        save(statuses);
    }
}
