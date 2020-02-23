package com.yeongjae.damoim.global.error.handler;

import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e){
        ErrorCodeType errorCodeType = e.getErrorCodeType();
        ErrorResponse errorResponse = ErrorResponse.of(errorCodeType);
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCodeType.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        ErrorCodeType errorCodeType = ErrorCodeType.UNKNOWN;
        ErrorResponse errorResponse = ErrorResponse.of(errorCodeType);
        log.error(e.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.valueOf(errorCodeType.getStatus()));
    }
}
