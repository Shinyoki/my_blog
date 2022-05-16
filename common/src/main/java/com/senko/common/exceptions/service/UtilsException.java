package com.senko.common.exceptions.service;

import com.senko.common.exceptions.base.BaseException;

/**
 * 工具类内部异常
 *
 * @author senko
 * @date 2022/5/9 13:37
 */
public class UtilsException extends RuntimeException{
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilsException(Throwable e)
    {
        super(e.getMessage(), e);
    }

    public UtilsException(String message)
    {
        super(message);
    }

    public UtilsException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
