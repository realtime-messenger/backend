package com.example.backend.service.crud;

import com.example.backend.model.blackList.BlackList;
import com.example.backend.repository.BlackListRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.crud.Interface.IDataAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlackListCrudService implements IDataAccessible<BlackList, Long> {

    private final BlackListRepository blackListRepository;

    @Autowired
    public BlackListCrudService(BlackListRepository blackListRepository, UserRepository userRepository) {
        this.blackListRepository = blackListRepository;
    }

    @Override
    public BlackList save(BlackList entity) {
        return blackListRepository.save(entity);
    }

    @Override
    public Optional<BlackList> getById(Long id) {
        return blackListRepository.findById(id);
    }

    @Override
    public void delete(BlackList entity) {
        blackListRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        blackListRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return blackListRepository.existsById(id);
    }
}
