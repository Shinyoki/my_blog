package com.senko.common.exceptions.user;

/**
 * 该账户已被金庸
 * @author senko
 * @date 2022/4/26 12:00
 */
public class UserStatusForbidden extends UserException{
    public UserStatusForbidden() {
        super("user.status.forbidden", null);
    }
}
