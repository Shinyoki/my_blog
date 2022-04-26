package com.senko.framework.config.security;

import com.senko.framework.config.security.handler.AuthenticationSuccessHandlerImpl;
import com.senko.framework.config.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

    //登录成功受理器
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    //认证失败受理器
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    //注销受理器
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    //权限不足，访问受限受理器
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    //认证失败处理器
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 验证者
     *  <a href="https://segmentfault.com/a/1190000012557493">详细：一篇非常不错的文章</a>
     * AuthenticationManager一般不直接认证，AuthenticationManager接口的常用实现类ProviderManager 内部会维护一个List<AuthenticationProvider>列表，
     * 存放多种认证方式，实际上这是委托者模式的应用（Delegate）
     * 重新注入AuthenticationManager，有时IOC容器里不会存在该实例
     */
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

    /**
     * http 安全权限管理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登录&注销
        http.formLogin()
                //默认就是 /login
                .loginProcessingUrl("/login");
                //登录成功受理
//                .successHandler()
                //登录失败受理
//                .failureHandler()
    }
}
