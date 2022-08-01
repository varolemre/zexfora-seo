package com.codingbytime.zexfora.control.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

import static org.springframework.web.servlet.HandlerMapping.LOOKUP_PATH;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> implements Serializable {
    private static final long serialVersionUID = -3636335462769574783L;
    private transient T data;
    private int status;
    private String error;
    private String message;
    private String path;
    private String timestamp;
    private Map<String, String> validationErrors;

    public ResponseEntity<CustomResponse<T>> responseOK(T data) {
        RequestAttributes reqAttributes = RequestContextHolder.currentRequestAttributes();
        CustomResponse<T> response = new CustomResponse<>();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setStatus(HttpStatus.OK.value());
        response.setError(null);
        response.setMessage("Success");
        response.setPath(Objects.requireNonNull(reqAttributes.getAttribute(LOOKUP_PATH, 0)).toString());
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    public CustomResponse<T> responseError(String message, String err, String path, int status) {
        CustomResponse<T> response = new CustomResponse<>();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setStatus(status);
        response.setPath(path);
        response.setError(err);
        response.setMessage(message);
        return response;
    }

    // Overload
    public CustomResponse<T> responseError(String message, String err, String path, int status, Map<String, String> validationErrors) {
        CustomResponse<T> response = new CustomResponse<>();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setStatus(status);
        response.setPath(path);
        response.setError(err);
        response.setMessage(message);
        response.setValidationErrors(validationErrors);
        return response;
    }

    public ResponseEntity<CustomResponse<T>> responseCreated(T data) {
        RequestAttributes reqAttributes = RequestContextHolder.currentRequestAttributes();
        CustomResponse<T> response = new CustomResponse<>();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setStatus(HttpStatus.CREATED.value());
        response.setError(null);
        response.setMessage("Success");
        response.setPath(Objects.requireNonNull(reqAttributes.getAttribute(LOOKUP_PATH, 0)).toString());
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

