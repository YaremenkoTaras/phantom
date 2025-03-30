package com.tyaremenko.user_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String status;
}
