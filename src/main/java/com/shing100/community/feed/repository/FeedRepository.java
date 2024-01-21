package com.shing100.community.feed.repository;

import com.shing100.community.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface FeedRepository extends JpaRepository<Feed, Long>, QuerydslPredicateExecutor<Feed> {

}
