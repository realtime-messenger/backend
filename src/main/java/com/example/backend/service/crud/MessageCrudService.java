package com.example.backend.service.crud;

import com.example.backend.exceptions.InternalErrorException;
import com.example.backend.model.message.BaseMessage;
import com.example.backend.model.message.PrivateMessage;
import com.example.backend.model.message.PublicMessage;
import com.example.backend.repository.*;
import com.example.backend.service.crud.Interface.IDataAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageCrudService implements IDataAccessible<BaseMessage, String> {

    private final PrivateMessageRepository privateMessageRepository;
    private final PublicMessageRepository publicMessageRepository;
    private final BaseMessageRepository baseMessageRepository;

    @Autowired
    public MessageCrudService(PrivateMessageRepository privateMessageRepository, PublicMessageRepository publicMessageRepository, BaseMessageRepository baseMessageRepository, MongoTemplate mongoTemplate) {
        this.privateMessageRepository = privateMessageRepository;
        this.publicMessageRepository = publicMessageRepository;
        this.baseMessageRepository = baseMessageRepository;
    }

    @Override
    public BaseMessage save(BaseMessage entity) {
        BaseMessage result;

        if (entity instanceof PrivateMessage) {
            result = privateMessageRepository.save((PrivateMessage) entity);
        }
        else if (entity instanceof PublicMessage) {
            result = publicMessageRepository.save((PublicMessage) entity);
        }
        else {
            throw new InternalErrorException();
        }

        return result;
    }

    @Override
    public Optional<BaseMessage> getById(String id) {
        return baseMessageRepository.findById(id);
    }

    @Override
    public void delete(BaseMessage entity) {
        baseMessageRepository.delete(entity);
    }

    @Override
    public void deleteById(String id) {
        baseMessageRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return baseMessageRepository.existsById(id);
    }

    public List<BaseMessage> getByChatId (String chatId) {
        return baseMessageRepository.getBaseMessagesByChatId(chatId);
    }

    public List<BaseMessage> getChatMessages (
            String chatId,
            long skip,
            long limit
    ) {
        return baseMessageRepository.getBaseMessagesByChatId(chatId, skip, limit);
    }
}
