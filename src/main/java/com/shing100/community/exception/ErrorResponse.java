package com.shing100.community.exception;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class ErrorResponse {
    private String errorMessage;
    private ErrorCode errorCode;
}
