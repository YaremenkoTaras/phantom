package com.tyaremenko.user_service.repository;

import com.tyaremenko.user_service.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

        Iterable<User> findUsersByIdIn(Iterable<String> userIds);
}
