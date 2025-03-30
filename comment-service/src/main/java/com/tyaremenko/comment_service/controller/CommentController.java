package com.tyaremenko.comment_service.controller;

import com.tyaremenko.comment_service.domain.Comment;
import com.tyaremenko.comment_service.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Iterable<Comment>> getCommentsForContent(@RequestParam Long contentId) {
        Iterable<Comment> users = commentService.getCommentsForContent(contentId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
