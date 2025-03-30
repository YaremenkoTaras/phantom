package com.tyaremenko.content_service.controller;

import com.tyaremenko.content_service.domain.Content;
import com.tyaremenko.content_service.dto.ContentResponse;
import com.tyaremenko.content_service.service.ContentService;
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
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/contents")
public class ContentController {
    private ContentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long id) {
        return commentService.getContent(id)
                             .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        return new ResponseEntity<>(commentService.createContent(content), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Content> updateContent(@RequestBody Content content) {
        return new ResponseEntity<>(commentService.updateContent(content), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        commentService.deleteContent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
