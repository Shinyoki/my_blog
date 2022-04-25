package com.senko.framework.config.web;

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
        //TODO 限流拦截器 & 分页请求拦截器
    }


}
