package com.tyaremenko.content_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@With
public class CommentDto {
    String userId;
    String id;
    String parentCommentId;
    String content;
    Long likes;
    Long dislikes;
}
