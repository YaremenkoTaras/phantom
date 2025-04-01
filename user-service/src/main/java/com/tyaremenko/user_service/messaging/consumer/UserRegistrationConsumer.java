package com.tyaremenko.user_service.messaging.consumer;

import com.tyaremenko.user_service.domain.User;
import com.tyaremenko.user_service.messaging.domain.UserRegistrationMessage;
import com.tyaremenko.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationConsumer {
    private final UserService userService;

    @RabbitListener(queues = "userRegistrationQueue")
    public void registerUser(UserRegistrationMessage message) {
        User user = new User();
        user.setNickname(message.getNickname());
        user.setEmail(message.getEmail());
        user.setPassword(message.getPassword());
        user.setFirstName(message.getFirstName());
        user.setLastName(message.getLastName());
        userService.createUser(user);
    }
}
