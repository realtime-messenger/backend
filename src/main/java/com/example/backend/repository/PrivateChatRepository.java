package com.example.backend.repository;

import com.example.backend.model.chat.PrivateChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivateChatRepository extends MongoRepository<PrivateChat, String> {

    // {type: "PRIVATE", userId: {$all: ["2", "1"]}}
    @Query("{type: 'PRIVATE', userId: {$all: [?0, ?1]}}")
    Optional<PrivateChat> getPrivateChatOfUsers (
            String firstUserId,
            String secondUserId
    );
}