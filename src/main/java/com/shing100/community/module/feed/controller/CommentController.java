package com.shing100.community.module.feed.controller;

import com.shing100.community.module.feed.domain.Comment;
import com.shing100.community.module.feed.service.CommentService;
import com.shing100.community.module.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> createComment(@PathVariable Long id, Comment comment, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.createComment(id, comment, userDetails.getUser()));
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, Comment comment, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.updateComment(id, comment, userDetails.getUser()));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.deleteComment(id, userDetails.getUser()));
    }
}
