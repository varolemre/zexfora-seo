package com.codingbytime.zexfora.control.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CustomUnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1013462121627170323L;

    public CustomUnauthorizedException(String type) {
        super(type);
    }
}
