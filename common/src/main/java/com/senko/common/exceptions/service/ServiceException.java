package com.senko.common.exceptions.service;

/**
 * 业务异常
 * @author senko
 * @date 2022/5/23 10:35
 */
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause.getMessage(),cause);
    }
}
