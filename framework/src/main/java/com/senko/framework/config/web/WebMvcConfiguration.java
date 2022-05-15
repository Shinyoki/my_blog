package com.senko.framework.config.web;

import com.senko.common.utils.redis.RedisHandler;
import com.senko.framework.config.web.interceptor.AccessLimitHandlerInterceptor;
import com.senko.framework.config.web.interceptor.PageableHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc配置类
 * @author senko
 * @date 2022/4/25 7:46
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private RedisHandler redisHandler;

    @Bean
    public AccessLimitHandlerInterceptor accessLimitHandlerInterceptor() {
        return new AccessLimitHandlerInterceptor(redisHandler);
    }

    /**
     * 后端 跨域支持
     * @param registry  跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //all routers
        registry.addMapping("/**")
                //允许携带验证信息头     true
                .allowCredentials(true)
                //允许的headers        *
                .allowedHeaders("*")
                //允许的域              *
                .allowedOrigins("*")
                //允许请求方式           *
                .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageableHandlerInterceptor());
        registry.addInterceptor(accessLimitHandlerInterceptor());
    }


}
