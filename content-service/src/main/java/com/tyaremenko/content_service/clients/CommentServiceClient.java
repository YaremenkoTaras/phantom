package com.tyaremenko.content_service.clients;

import com.tyaremenko.content_service.dto.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "comment-service")
public interface CommentServiceClient {

    @PostMapping("/comments")
    Set<CommentDto> getCommentsForContent(@RequestParam String contentId);
}
