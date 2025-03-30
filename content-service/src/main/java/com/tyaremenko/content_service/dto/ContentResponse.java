package com.tyaremenko.content_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@With
public class ContentResponse {
    String id;
    String content;
    Long likes;
    Long dislikes;
    Set<CommentResponse> comments;
    UserResponse author;
}
