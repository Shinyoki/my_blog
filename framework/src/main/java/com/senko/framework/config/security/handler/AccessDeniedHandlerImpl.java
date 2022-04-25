package com.senko.framework.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.senko.common.core.AjaxResult;
import com.senko.common.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * 访问被拒，权限不足处理器
 * 403
 *
 * @author senko
 * @date 2022/4/25 12:36
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = MessageFormat.format("请求访问：{0}，权限不足，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(message)));
    }
}
