package com.senko.framework.config.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.senko.common.annotation.AccessLimit;
import com.senko.common.constants.StatusCodeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.redis.RedisHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 访问限流拦截器：依赖redis实现
 * @author senko
 * @date 2022/5/15 17:01
 */
public class AccessLimitHandlerInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLimitHandlerInterceptor.class);

    private RedisHandler redisHandler;

    @Autowired
    public AccessLimitHandlerInterceptor(RedisHandler redisHandler) {
        this.redisHandler = redisHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit annotation = handlerMethod.getMethod().getAnnotation(AccessLimit.class);
            if (Objects.nonNull(annotation)) {
                //有限流需求
                int seconds = annotation.seconds();
                int maxCount = annotation.maxCount();

                //uuid : ipAddress + method.name
                String redisKey = IpUtils.getIpAddressFromRequest(request) + handlerMethod.getMethod().getName();
                try {
                    Long curLimitCount = redisHandler.incrementAndResetExpire(redisKey, seconds);
                    //超过限制
                    if (curLimitCount > maxCount) {
                        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error("请求过于频繁，请稍后再试")));
                        LOGGER.warn(redisKey + "请求次数超过" + seconds + "秒/" + maxCount + "次！");
                        return false;
                    }
                } catch (Exception e) {
                    LOGGER.error("redis异常：" + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}
