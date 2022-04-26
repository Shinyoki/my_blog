package com.senko.common.exceptions.user;

/**
 * 旧密码不正确
 * @author senko
 * @date 2022/4/26 11:57
 */
public class UserOldPasswordNotMatchException extends UserException{
    public UserOldPasswordNotMatchException() {
        super("user.old.password.not.match",null);
    }
}
