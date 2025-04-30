package com.example.backend.repository;

import com.example.backend.model.message.PublicMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicMessageRepository extends MongoRepository<PublicMessage, String> {

}