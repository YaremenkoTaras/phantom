package com.tyaremenko.user_service.messaging.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
public class UserRegistrationMessage {
    String nickname;
    String email;
    String password;
    String firstName;
    String lastName;
}
