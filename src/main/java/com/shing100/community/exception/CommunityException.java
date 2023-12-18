package com.shing100.community.exception;

import lombok.Getter;

@Getter
public class CommunityException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public CommunityException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public CommunityException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.message = detailMessage;
    }
}
