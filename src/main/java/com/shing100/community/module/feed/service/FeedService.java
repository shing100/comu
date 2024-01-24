package com.shing100.community.module.feed.service;

import com.shing100.community.module.exception.CommunityException;
import com.shing100.community.module.exception.ErrorCode;
import com.shing100.community.module.feed.domain.Feed;
import com.shing100.community.module.feed.repository.FeedRepository;
import com.shing100.community.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public List<Feed> getFeeds() {
        return feedRepository.findAll();
    }

    public Feed createFeed(Feed feed, User user) {
        feed.setUser(user);
        return feedRepository.save(feed);
    }

    public Feed getFeedDetail(Long id, User user) {
        Optional<Feed> feed = feedRepository.findById(id);
        if (feed.isEmpty())
            throw new CommunityException(ErrorCode.FEED_NOT_FOUND_EXCEPTION);

        if (feedRepository.findById(id).get().getUser().getId() != user.getId()) {
            throw new CommunityException(ErrorCode.NOT_FOUND_FEED_USER_EXCEPTION);
        }

        return feed.get();
    }

    public Feed updateFeed(Long id, Feed feed, User user) {
        Feed findFeed = feedRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new CommunityException(ErrorCode.NOT_FOUND_FEED_USER_EXCEPTION));

        if (findFeed.getUser().getId() != user.getId())
            throw new CommunityException(ErrorCode.NOT_FOUND_FEED_USER_EXCEPTION);

        findFeed.update(feed);
        feedRepository.flush();
        return findFeed;
    }

    public Boolean deleteFeed(Long id, User user) {
        Feed findFeed = feedRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new CommunityException(ErrorCode.NOT_FOUND_FEED_USER_EXCEPTION));

        if (findFeed.getUser().getId() != user.getId())
            throw new CommunityException(ErrorCode.NOT_FOUND_FEED_USER_EXCEPTION);

        feedRepository.delete(findFeed);
        return true;
    }
}
