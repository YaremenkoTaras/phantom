package com.tyaremenko.comment_service.service;

import com.tyaremenko.comment_service.domain.Comment;
import com.tyaremenko.comment_service.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {
    private CommentRepository commentRepository;

    public Comment createComment(Comment user) {
        return commentRepository.save(user);
    }

    public Comment updateComment(Comment user) {
        return commentRepository.save(user);
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> getComment(String id) {
        return commentRepository.findById(id);
    }

    public Iterable<Comment> getCommentsForContent(String contentId) {
        return commentRepository.findCommentByContentId(contentId);
    }
}
