package com.senko.framework.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.senko.common.core.AjaxResult;
import com.senko.common.utils.http.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * 登录失败处理器
 *
 * @author senko
 * @date 2022/4/25 12:40
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error("登陆失败!请检查用户名或密码是否输入正确！")));
    }
}
