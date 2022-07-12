package com.senko.framework.strategy.context;

import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.enums.LoginTypeEnum;
import com.senko.framework.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 第三方登录上下文
 * @author senko
 * @date 2022/7/12 6:35
 */
@Service
public class SocialLoginStrategyContext {

    @Autowired
    private Map<String, LoginStrategy> loginStrategyMap;

    public UserLoginInfoDTO executeLogin(String data, LoginTypeEnum loginType) {
        return loginStrategyMap.get(loginType.getStrategy()).login(data);
    }
}
