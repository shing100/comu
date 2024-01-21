package com.shing100.community.module.feed.controller;

import com.shing100.community.module.feed.domain.Comment;
import com.shing100.community.module.feed.domain.Feed;
import com.shing100.community.module.feed.service.LikesService;
import com.shing100.community.module.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/feed/{id}")
    public ResponseEntity<?> likeFeed(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Feed feed = likesService.likeFeed(id, userDetails.getUser());
        return ResponseEntity.ok(feed);
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> CommentLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment commnet = likesService.likeComment(id, userDetails.getUser());
        return ResponseEntity.ok(commnet);
    }
}
