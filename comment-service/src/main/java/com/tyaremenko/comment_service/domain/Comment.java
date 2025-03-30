package com.tyaremenko.comment_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    Long userId;
    Long contentId;
    Long parentCommentId;
    String content;
    long likes;
    long dislikes;
}
