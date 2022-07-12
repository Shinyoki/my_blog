package com.senko.framework.strategy;

import com.senko.common.core.dto.UserLoginInfoDTO;

/**
 * 登录策略
 * @author senko
 * @date 2022/7/12 6:32
 */
public interface LoginStrategy {

    /**
     * 登录
     * @param data
     */
    UserLoginInfoDTO login(String data);
}
