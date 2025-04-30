package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
public class BaseMongoEntity {
    @Id
    private String id;
    private LocalDateTime dateCreated;

    public BaseMongoEntity() {
        this.dateCreated = LocalDateTime.now();
    }

}