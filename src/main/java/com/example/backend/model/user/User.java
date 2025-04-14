package com.example.backend.model.user;

import com.example.backend.model.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "users")
public class User extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private byte[] password;

    public User() {}

    public User(
            String firstName,
            String lastName,
            String middleName,
            String login,
            byte[] password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
    }

    public User(
            String firstName,
            String lastName,
            String login,
            byte[] password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
    }
}