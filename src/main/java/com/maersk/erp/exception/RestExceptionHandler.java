package com.maersk.erp.exception;

import com.maersk.erp.exception.apierror.ApiError;
import com.maersk.erp.response.CustomResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({ParameterNotValidException.class})
    public ResponseEntity<Object> parameterNotValidExceptionHandler(ParameterNotValidException ex) {
        ApiError error = new ApiError(ex.getCode(), ex.getMessage()
                , HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return buildResponseEntity(error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandler(Exception ex) {
        ApiError error = new ApiError("ER101", "Sorry there was a problem processing your " +
                "request"
            , HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(CustomResponseEntity.builder().code(apiError.getCode())
                .message(apiError.getMessage()).build(), apiError.getStatus());
    }
}
