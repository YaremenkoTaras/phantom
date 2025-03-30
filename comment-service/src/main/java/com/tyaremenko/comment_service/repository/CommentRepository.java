package com.tyaremenko.comment_service.repository;

import com.tyaremenko.comment_service.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findCommentByContentId(Long contentId);
}
