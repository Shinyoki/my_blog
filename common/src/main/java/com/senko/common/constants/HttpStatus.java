package com.senko.common.constants;

/**
 * @author senko
 * @date 2022/4/24 21:17
 */
public enum HttpStatus {
    /**
     * OK
     */
    OK(200, "成功"),
    /**
     * AccessTokenError
     */
    AccessTokenError(400, "用户登录令牌失效"),
    /**
     * UNAUTHORIZED
     */
    UNAUTHORIZED(401, "用户未登录"),
    /**
     * UNAUTHORIZED
     */
    AuthError(402, "用户名或密码错误"),
    /**
     * InnerError
     */
    InnerError(500, "系统内部错误"),
    /**
     * ParameterValidError
     */
    ParameterValidError(501, "参数验证错误"),
    /**
     * 404
     */
    NotFound(404, "资源未找到"),
    /**
     * AccessDenied
     */
    AccessDenied(502, "用户没有权限访问");

    int code;

    String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

