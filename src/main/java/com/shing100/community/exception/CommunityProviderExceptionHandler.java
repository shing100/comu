package com.shing100.community.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CommunityProviderExceptionHandler {

    @ExceptionHandler(CommunityException.class)
    public ErrorResponse descriptionExceptionHandler(CommunityException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}", e.getErrorCode(), request.getRequestURI(), e.getMessage());
        return ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class})
    public ErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return ErrorResponse.builder()
                .errorCode(ErrorCode.INVALID_REQUEST_EXCEPTION)
                .errorMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return ErrorResponse.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_EXCEPTION)
                .errorMessage(e.getMessage())
                .build();
    }
}
