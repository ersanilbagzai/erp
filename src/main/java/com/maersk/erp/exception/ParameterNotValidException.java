package com.maersk.erp.exception;

import org.springframework.http.HttpStatus;

public class ParameterNotValidException extends CommonException {

    public ParameterNotValidException(String code, String message, int httpStatusCode, HttpStatus status) {
        super(code, message, httpStatusCode, status);
    }
}
