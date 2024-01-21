package com.shing100.community.feed.repository;

import com.shing100.community.feed.domain.Comment;
import com.shing100.community.feed.domain.Fav;
import com.shing100.community.feed.domain.Feed;
import com.shing100.community.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Fav, Long> {

    Optional<Fav> findByFeedAndUser(Feed feed, User user);

    Optional<Fav> findByCommentAndUser(Comment comment, User user);
}
