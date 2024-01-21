package com.shing100.community.module.exception;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class ErrorResponse {
    private String errorMessage;
    private ErrorCode errorCode;
}
