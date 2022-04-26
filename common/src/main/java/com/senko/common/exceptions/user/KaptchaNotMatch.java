package com.senko.common.exceptions.user;

/**
 * 验证码错误
 * @author senko
 * @date 2022/4/26 11:59
 */
public class KaptchaNotMatch extends UserException {
    public KaptchaNotMatch() {
        super("user.kaptcha.not.match", null);
    }
}
