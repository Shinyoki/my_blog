package com.senko.framework.config.web.interceptor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senko.common.constants.CommonConstants;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 分页拦截器
 *
 * @author senko
 * @date 2022/5/15 14:37
 */
public class PageableHandlerInterceptor implements HandlerInterceptor {
    /**
     * 请求预处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求中提取current & size
        String current = request.getParameter(CommonConstants.CURRENT_TAG);
        String size = Optional.ofNullable(request.getParameter(CommonConstants.SIZE_TAG)).orElse(CommonConstants.DEFAULT_SIZE);

        if (StringUtils.isNotBlank(current)) {
            Page page = new Page(Long.parseLong(current), Long.parseLong(size));
            PageUtils.setCurrentPage(page);
        }

        return true;
    }

    /**
     * 请求后置处理，因为在当前会话session里线程的ThreadLocal会一直存在，
     * 所以需要通过拦截器在处理完请求后及时清理ThreadLocal，防止内存泄漏
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        PageUtils.remove();
    }
}
