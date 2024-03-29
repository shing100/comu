package com.shing100.community.module.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum ErrorCode {
    INTERNAL_SERVER_EXCEPTION("서버에 오류가 발생했습니다."),
    INVALID_REQUEST_EXCEPTION("잘못된 요청입니다."),

    EXPIRED_TOKEN_EXCEPTION("만료된 토큰입니다."),
    INVALID_TOKEN_EXCEPTION("유효하지 않은 토큰입니다."),
    INVALID_TOKEN_FORMAT_EXCEPTION("유효하지 않은 토큰 형식입니다."),
    INVALID_TOKEN_NOT_FOUND_EXCEPTION("토큰을 찾을 수 없습니다."),

    USER_NOT_FOUND_EXCEPTION("해당 유저를 찾을 수 없습니다."),
    USER_ALREADY_EXIST_EXCEPTION("이미 존재하는 유저입니다."),
    USER_EMAIL_NOT_VALID_EXCEPTION("이메일이 유효하지 않습니다."),
    USER_PASSWORD_NOT_VALID_EXCEPTION("비밀번호가 유효하지 않습니다."),

    FEED_NOT_FOUND_EXCEPTION("해당 피드를 찾을 수 없습니다."),
    FEED_ALREADY_EXIST_EXCEPTION("이미 존재하는 피드입니다."),
    LIKE_ALREADY_EXIST_EXCEPTION("이미 존재하는 좋아요입니다."),
    NOT_FOUND_FEED_USER_EXCEPTION("해당 피드의 유저를 찾을 수 없습니다."),

    COMMENT_NOT_FOUND_EXCEPTION("해당 댓글을 찾을 수 없습니다."),
    COMMENT_ALREADY_EXIST_EXCEPTION("이미 존재하는 댓글입니다."),
    COMMENT_NOT_VALID_EXCEPTION("댓글이 유효하지 않습니다."),
    COMMENT_NOT_OWNER_EXCEPTION("댓글의 소유자가 아닙니다.");

    private final String message;
}
