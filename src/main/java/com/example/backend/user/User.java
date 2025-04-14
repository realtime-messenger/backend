package com.example.backend.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

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