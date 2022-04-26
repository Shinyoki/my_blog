package com.senko.common.exceptions.user;

import com.senko.common.exceptions.base.BaseException;

/**
 * 用户异常
 *
 * @author senko
 * @date 2022/4/26 11:56
 */
public class UserException extends BaseException {
    public UserException(String code, Object[] args) {
        super("user", code, args, "");
    }
}
