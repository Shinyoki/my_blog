package com.senko.common.exceptions.base;

import com.senko.common.utils.i18n.I18nUtils;
import com.senko.common.utils.string.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 基本异常
 *
 * @author senko
 * @date 2022/4/26 9:21
 */
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * 所属模块
     * user
     */
    private String module;

    /**
     * 错误码
     * user.password.not.match
     */
    private String code;

    /**
     * 错误码所带参数
     */
    private Object[] args;

    /**
     * 错误题是
     */
    private String defaultMessage;

    /**
     * 自定义异常message
     * @return
     */
    @Override
    public String getMessage() {
        String resultMessage = null;
        if (StringUtils.isNotEmpty(code)) {
            //如果拥有所属模块
            resultMessage = I18nUtils.message(code, args);
        } else {
            //否则使用默认
            resultMessage = defaultMessage;
        }
        return resultMessage;
    }
}
