package com.tyaremenko.content_service.clients;

import com.tyaremenko.content_service.dto.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "comment-service", url = "${comment-service.url}")
public interface CommentServiceClient {

    @GetMapping("/comments")
    Set<CommentDto> getCommentsForContent(@RequestParam Long contentId);
}
