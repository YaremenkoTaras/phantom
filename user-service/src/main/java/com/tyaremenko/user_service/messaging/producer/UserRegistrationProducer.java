package com.tyaremenko.user_service.messaging.producer;

import com.tyaremenko.user_service.domain.User;
import com.tyaremenko.user_service.messaging.domain.UserRegistrationMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendUserRegistrationMessage(User user) {
        UserRegistrationMessage message = UserRegistrationMessage.builder()
                                                                 .nickname(user.getNickname())
                                                                 .email(user.getEmail())
                                                                 .password(user.getPassword())
                                                                 .firstName(user.getFirstName())
                                                                 .lastName(user.getLastName())
                                                                 .build();
        rabbitTemplate.convertAndSend("userRegistrationQueue", message);
    }
}
