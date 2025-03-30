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

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Iterable<Comment> getCommentsForContent(Long contentId) {
        return commentRepository.findCommentByContentId(contentId);
    }
}
