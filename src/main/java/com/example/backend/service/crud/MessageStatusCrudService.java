package com.example.backend.service.crud;

import com.example.backend.model.message.Message;
import com.example.backend.model.messageStatus.MessageStatus;
import com.example.backend.model.user.User;
import com.example.backend.repository.MessageStatusRepository;
import com.example.backend.service.business.MessageService;
import com.example.backend.service.crud.Interface.IDataAccessible;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageStatusCrudService implements IDataAccessible<MessageStatus, String> {

    private final MessageStatusRepository messageStatusRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MessageStatusCrudService(MessageStatusRepository messageStatusRepository, MongoTemplate mongoTemplate) {
        this.messageStatusRepository = messageStatusRepository;
        this.mongoTemplate = mongoTemplate;
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

    private class Result {
        private MessageStatus status;
    }

    public List<MessageStatus> getStatusesForLastMessages (String userId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("userId").is(userId).and("isDeleted").is(false)
                ),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "dateCreated")),
                Aggregation.group("chatId").first("$$ROOT").as("status"),
                Aggregation.project("$status")
        );

        var result = mongoTemplate.aggregate(
                aggregation,
                "message_status",
                Result.class
        ).getMappedResults();

        List<MessageStatus> statuses = new ArrayList<>();

        for (Result resultStatus : result) {
            statuses.add(resultStatus.status);
        }

        return statuses;
    }

    public void markDeleted (Message message) {
        List<MessageStatus> statuses = messageStatusRepository.findMessageStatusByMessageId(message.getId());
        statuses.forEach(
                (messageStatus) -> {
                    messageStatus.setIsDeleted(true);
                }
        );
        messageStatusRepository.saveAll(statuses);
    }

    public void markDeleted (Message message, User user) {
        Optional<MessageStatus> opt_Status = messageStatusRepository.findMessageStatusByMessageIdAndUserId(message.getId(), String.valueOf(user.getId()));
        if (opt_Status.isEmpty()) return;
        var status = opt_Status.get();
        status.setIsDeleted(true);
        messageStatusRepository.save(status);
    }

}
