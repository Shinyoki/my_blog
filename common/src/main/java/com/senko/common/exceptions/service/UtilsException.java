package com.senko.common.exceptions.service;

import com.senko.common.exceptions.base.BaseException;

/**
 * 工具类内部异常
 *
 * @author senko
 * @date 2022/5/9 13:37
 */
public class UtilsException extends BaseException {
    public UtilsException() {
        super("utils", "utils.exception", null, "");
    }
}
