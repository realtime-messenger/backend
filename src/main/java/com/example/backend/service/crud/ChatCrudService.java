package com.example.backend.service.crud;

import com.example.backend.model.chat.BaseChat;
import com.example.backend.model.chat.PrivateChat;
import com.example.backend.model.chat.PublicChat;
import com.example.backend.repository.BaseChatRepository;
import com.example.backend.repository.PrivateChatRepository;
import com.example.backend.repository.PublicChatRepository;
import com.example.backend.service.crud.Interface.IDataAccessible;
import com.example.backend.exceptions.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ChatCrudService implements IDataAccessible<BaseChat, String> {

    private final PrivateChatRepository privateChatRepository;
    private final PublicChatRepository publicChatRepository;
    private final BaseChatRepository baseChatRepository;

    @Autowired
    public ChatCrudService(PrivateChatRepository privateChatRepository, PublicChatRepository publicChatRepository, BaseChatRepository baseChatRepository) {
        this.privateChatRepository = privateChatRepository;
        this.publicChatRepository = publicChatRepository;
        this.baseChatRepository = baseChatRepository;
    }

    @Override
    public BaseChat save(BaseChat entity) {
        BaseChat result;

        if (entity instanceof PrivateChat) {
            result = privateChatRepository.save((PrivateChat) entity);
        }
        else if (entity instanceof PublicChat) {
            result = publicChatRepository.save((PublicChat) entity);
        }
        else {
            throw new InternalErrorException();
        }

        return result;
    }

    @Override
    public Optional<BaseChat> getById(String id) {
        return baseChatRepository.findById(id);
    }

    @Override
    public void delete(BaseChat entity) {
        baseChatRepository.delete(entity);
    }

    @Override
    public void deleteById(String id) {
        baseChatRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return baseChatRepository.existsById(id);
    }

    public boolean privateChatExists(
            String firstUserId,
            String secondUserId
    ) {
        var result = privateChatRepository.getPrivateChatOfUsers(
                firstUserId,
                secondUserId
        );

        return result.isPresent();
    }

    public Optional<PrivateChat> getPrivateChatOfUsers (
            String firstUserId,
            String secondUserId
    ) {
        return privateChatRepository.getPrivateChatOfUsers(
                firstUserId,
                secondUserId
        );
    }

}
