package com.tyaremenko.content_service.clients;

import com.tyaremenko.content_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/users/search")
    Set<UserDto> searchUsers(Set<String> ids);
}
