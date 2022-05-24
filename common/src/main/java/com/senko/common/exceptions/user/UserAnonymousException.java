package com.senko.common.exceptions.user;

/**
 * 匿名用户操作异常
 * @author senko
 * @date 2022/5/24 8:55
 */
public class UserAnonymousException extends RuntimeException{
    public UserAnonymousException(String message) {
        super(message);
    }

    public UserAnonymousException(Throwable cause) {
        super(cause);
    }
}
