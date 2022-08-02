package com.codingbytime.zexfora.control.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3483125913993536588L;

    public CustomNotFoundException(String type) {
        super(type);
    }
}
