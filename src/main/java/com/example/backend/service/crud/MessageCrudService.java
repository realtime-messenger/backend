package com.example.backend.service.crud;

import com.example.backend.model.message.Message;
import com.example.backend.repository.*;
import com.example.backend.service.crud.Interface.IDataAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageCrudService implements IDataAccessible<Message, String> {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageCrudService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message entity) {
        Message result;

        result = messageRepository.save(entity);

        return result;
    }

    @Override
    public Optional<Message> getById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public void delete(Message entity) {
        messageRepository.delete(entity);
    }

    @Override
    public void deleteById(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return messageRepository.existsById(id);
    }

    public List<Message> getByChatId (String chatId) {
        return messageRepository.getBaseMessagesByChatId(chatId);
    }

    public List<Message> getChatMessages (
            String chatId,
            long skip,
            long limit
    ) {
        return messageRepository.getBaseMessagesByChatId(chatId, skip, limit);
    }
}
