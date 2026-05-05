package com.vocab_enhancer.academy.exceptions;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
}
