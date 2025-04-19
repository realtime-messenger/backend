package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private LocalDateTime dateCreated;

}