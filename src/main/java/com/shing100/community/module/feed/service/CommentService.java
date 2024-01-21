package com.shing100.community.module.feed.service;

import com.shing100.community.module.feed.domain.Comment;
import com.shing100.community.module.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    // feed id로 feed를 찾고, comment를 생성한다.
    public Object createComment(Long id, Comment comment, User user) {
        return null;
    }

    public Object updateComment(Long id, Comment comment, User user) {
        return null;
    }

    public Object deleteComment(Long id, User user) {
        return null;
    }
}
