package com.senko.framework.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.senko.common.core.AjaxResult;
import com.senko.common.utils.http.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功处理器
 *
 * CSRF开启时SpringSecurity不支持 Get 形式的 /logout 请求，否则抛出404。
 * Security的默认等处页是 /login?logout 。
 * <pre>
 *     http..csrf().disable()
 *     http.logout().permitAll()
 *     http.authorizeRequests().antMatchers("/login?logout").permitAll()
 * </pre>
 *  取消CSRF防护对该情况的影响。
 * @author senko
 * @date 2022/4/25 10:25
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //罢了，这回不整TOKEN验证，用原来的SessionRegistry吧
        //统统只返回数据，路由交给前端（
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
