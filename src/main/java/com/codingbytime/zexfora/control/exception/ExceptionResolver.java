package com.codingbytime.zexfora.control.exception;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionResolver {
    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleWebExchangeBindException(MethodArgumentNotValidException e, WebRequest webRequest, HttpServletRequest request) {
        log.error("Exception caught in handleWebExchangeBindException: " + e.getMessage());
        return handleBindingExceptions(webRequest, request);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleBindException(BindException e, WebRequest webRequest, HttpServletRequest request) {
        log.error("Exception caught in handleBindException: " + e.getMessage());
        return handleBindingExceptions(webRequest, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {
        log.error("Exception caught in handleResponseStatusException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getReason(), e.getStatus().getReasonPhrase(), request.getRequestURI(), e.getStatus().value());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CustomResponse<Object> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("Exception caught in handleAccessDeniedException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), "Access Denied", request.getRequestURI(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(RequestRejectedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleRequestRejectedException(RequestRejectedException e, HttpServletRequest request) {
        log.error("Exception caught in handleRequestRejectedException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), e.toString(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error("Exception caught in handleIllegalArgumentException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), e.toString(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomResponse<Object> handleCustomNotFoundException(CustomNotFoundException e, HttpServletRequest request) {
        log.error("Exception caught in handleCustomNotFoundException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), "Not Found", request.getRequestURI(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse<Object> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("Exception caught in handleBusinessException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), "Internal Server Error", request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CustomResponse<Object> handleCustomUnauthorizedException(CustomUnauthorizedException e, HttpServletRequest request) {
        log.error("Exception caught in handleCustomUnauthorizedException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), "Unauthorized", request.getRequestURI(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleCustomBadRequestException(CustomBadRequestException e, HttpServletRequest request) {
        log.error("Exception caught in handleCustomBadRequestException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getMessage(), "Bad Request", request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("Exception caught in handleDataIntegrityViolationException: " + e.getMessage());
        return new CustomResponse<>().responseError
            (e.getCause().getMessage(), "Duplicate Entry", request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    }

    private CustomResponse<Object> handleBindingExceptions(WebRequest webRequest, HttpServletRequest request) {
        Map<String, Object> errors = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        Map<String, String> validationErrors = new HashMap<>();
        if (errors.containsKey("errors")) {
            List<FieldError> fieldErrors = (List<FieldError>) errors.get("errors");
            for (FieldError fieldError : fieldErrors)
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CustomResponse<>().responseError("Error on fields", "Field validation error", request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), validationErrors);
    }
}
