package com.shing100.community.module.feed.controller;

import com.shing100.community.module.feed.domain.Feed;
import com.shing100.community.module.feed.service.FeedService;
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
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/feeds")
    public ResponseEntity<?> getFeeds() {
        return ResponseEntity.ok(feedService.getFeeds());
    }

    @PostMapping("/feed")
    public ResponseEntity<?> createFeed(@RequestBody Feed feed, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(feedService.createFeed(feed, userDetails.getUser()));
    }

    @GetMapping("/feed/{id}")
    public ResponseEntity<?> getFeed(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(feedService.getFeedDetail(id, userDetails.getUser()));
    }

    @PostMapping("/feed/{id}")
    public ResponseEntity<?> updateFeed(@PathVariable Long id, @RequestBody Feed feed, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(feedService.updateFeed(id, feed, userDetails.getUser()));
    }

    @DeleteMapping("/feed/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(feedService.deleteFeed(id, userDetails.getUser()));
    }
}
