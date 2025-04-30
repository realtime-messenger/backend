package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseRdbmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "date_created", nullable = false)
    protected LocalDateTime dateCreated;

    public BaseRdbmsEntity() {
        this.dateCreated = LocalDateTime.now();
    }

}