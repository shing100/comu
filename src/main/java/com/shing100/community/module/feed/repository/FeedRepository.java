package com.shing100.community.module.feed.repository;

import com.shing100.community.module.feed.domain.Feed;
import com.shing100.community.module.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface FeedRepository extends JpaRepository<Feed, Long>, QuerydslPredicateExecutor<Feed> {

    Optional<Feed> findByIdAndUser(Long id, User user);
}
