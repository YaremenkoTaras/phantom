package com.tyaremenko.user_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue
    Long id;
    String nickname;
    String firstName;
    String lastName;
    String email;
    String password;
    String role;
    String status;
}
