package com.shing100.community.feed.service;

import com.shing100.community.exception.CommunityException;
import com.shing100.community.exception.ErrorCode;
import com.shing100.community.feed.domain.Comment;
import com.shing100.community.feed.domain.Fav;
import com.shing100.community.feed.domain.Feed;
import com.shing100.community.feed.repository.CommentRepository;
import com.shing100.community.feed.repository.FeedRepository;
import com.shing100.community.feed.repository.LikesRepository;
import com.shing100.community.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final FeedRepository feedRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;

    public Feed likeFeed(Long id, User user) {
        Optional<Feed> feed = feedRepository.findById(id);
        if (feed.isEmpty()) throw new CommunityException(ErrorCode.FEED_NOT_FOUND_EXCEPTION);
        Optional<Fav> fav = likesRepository.findByFeedAndUser(feed.get(), user);
        if (fav.isEmpty()) {
            likesRepository.save(Fav.builder()
                    .feed(feed.get())
                    .user(user)
                    .build());
        } else {
            likesRepository.delete(fav.get());
            likesRepository.flush();
        };

        return feed.get();
    }

    public Comment likeComment(Long id, User user) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) throw new CommunityException(ErrorCode.COMMENT_NOT_FOUND_EXCEPTION);
        Optional<Fav> fav = likesRepository.findByCommentAndUser(comment.get(), user);
        if (fav.isEmpty()) {
            likesRepository.save(Fav.builder()
                    .comment(comment.get())
                    .user(user)
                    .build());
        } else {
            likesRepository.delete(fav.get());
            likesRepository.flush();
        };

        return comment.get();
    }
}
