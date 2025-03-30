package com.tyaremenko.user_service.service;

import com.tyaremenko.user_service.domain.User;
import com.tyaremenko.user_service.dto.SearchUsersRequest;
import com.tyaremenko.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> searchUsers(SearchUsersRequest searchRequest) {
        return userRepository.findUsersByIdIn(searchRequest.getUserIds());
    }
}
