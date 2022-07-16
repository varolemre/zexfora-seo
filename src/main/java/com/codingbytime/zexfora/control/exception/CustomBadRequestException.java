package com.codingbytime.zexfora.control.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {

    private static final long serialVersionUID = -8577389158406840548L;

    public CustomBadRequestException(String type) {
        super(type);
    }
}
