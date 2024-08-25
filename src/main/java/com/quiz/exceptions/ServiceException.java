package com.quiz.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private String errorCode;
    private String errMessage;

    public ServiceException(String errorCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errorCode = errorCode;
        this.errMessage = errMessage;
    }

    public ServiceException(String errorCode, String errMessage) {
        this.errorCode = errorCode;
        this.errMessage = errMessage;
    }
}
