package com.maersk.erp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String message;
    private final int httpStatusCode;
    private final HttpStatus status;
}
