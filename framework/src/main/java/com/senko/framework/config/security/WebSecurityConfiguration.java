package com.senko.framework.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * Security配置类
 *
 * security ==> extends WebSecurityConfigurerAdapter
 * mvc      ==> implements WebMvcConfigurer
 * @author senko
 * @date 2022/4/25 7:34
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //TODO Security配置类

    /** 重新注入AuthenticationManager，有时IOC容器里不会存在该实例 */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Session对话表：维护了principals & sessions
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
