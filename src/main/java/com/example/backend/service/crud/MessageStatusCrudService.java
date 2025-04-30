package com.example.backend.service.crud;

import com.example.backend.model.messageStatus.MessageStatus;
import com.example.backend.repository.MessageStatusRepository;
import com.example.backend.service.crud.Interface.IDataAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageStatusCrudService implements IDataAccessible<MessageStatus, String> {

    private final MessageStatusRepository messageStatusRepository;

    @Autowired
    public MessageStatusCrudService(MessageStatusRepository messageStatusRepository) {
        this.messageStatusRepository = messageStatusRepository;
    }

    @Override
    public MessageStatus save(MessageStatus entity) {
        MessageStatus result;
        result = messageStatusRepository.save(entity);
        return result;
    }

    @Override
    public Optional<MessageStatus> getById(String id) {
        return messageStatusRepository.findById(id);
    }

    @Override
    public void delete(MessageStatus entity) {
        messageStatusRepository.delete(entity);
    }

    @Override
    public void deleteById(String id) {
        messageStatusRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return messageStatusRepository.existsById(id);
    }

    public Optional<MessageStatus> getByMessageIdAndUserId(
            String messageId,
            String userId
    ) {
        return messageStatusRepository.findMessageStatusByMessageIdAndUserId(
                messageId,
                userId
        );
    }
}
