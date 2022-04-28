package com.senko.framework.config.security;

import com.senko.common.utils.spring.SpringUtils;
import com.senko.framework.config.security.handler.AuthenticationSuccessHandlerImpl;
import com.senko.framework.config.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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

    /**
     * Handlers
     * 认证相关
     */
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
     * Manager
     * 动态权限
     * 访问URL资源相关
     */
    //访问通过管理器
    @Autowired
    private AccessDecisionManager accessDecisionManager;

    //访问角色管理器
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Beans
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *  <a href="https://segmentfault.com/a/1190000012557493">详细：一篇非常不错的文章</a>
     * AuthenticationManager一般不直接认证，AuthenticationManager接口的常用实现类ProviderManager
     * 内部会维护一个List<AuthenticationProvider>列表，
     * 存放多种认证方式，实际上这是委托者模式的应用（Delegate）
     * 重新注入AuthenticationManager，有时IOC容器里不会存在该实例
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 全局的认证管理器
     * 需DetailsService和密码加密方式
     *
     * 重写该方法使用的UserDetailsService实例
     * 则是 方法中注入的实例，
     * 此时容器中的UserDetailsService实例将不起作用
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
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
     *
     * (一)
     * 基于SPeL表达式匹配的资源认证ExpressionUrlAuthorizationConfigurer
     * <a href="https://blog.csdn.net/andy_zhang2007/article/details/93376098">详细</a>
     *
     * （二）
     * FilterSecurityInterceptor（动态权限拦截器，过滤链的最后一部分）
     * 这个filter有几个要素，如下：
     * SecurityMetadataSource
     * AccessDecisionManager
     * AuthenticationManager
     * <a href="https://blog.csdn.net/liuminglei1987/article/details/107662200">FilterSecurityInterceptor</a>
     *
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登录
        http.formLogin()
                //默认就是 /login
                .loginProcessingUrl("/login")
                //登录成功受理
                .successHandler(authenticationSuccessHandler)
                //登录失败受理
                .failureHandler(authenticationFailureHandler);

        //注销
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler);

        //关闭 跨站请求防护
        http.csrf().disable();

        //动态权限：FilterSecurityInterceptor
        //得到ExpressionUrlAuthorizationConfigurer，这里所设置的权限属性其实是基于SpEL的权限表达式
        http.authorizeRequests()
                //对该Url表达式认证器配置 通过后置处理器 添加FilterSecurityInterceptor，
                //设定自定义的 访问权限来源MetadataSource和访问决定Decision
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(securityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                //ExpressionUrlAuthorizationConfigurer，不配置个authenticated() or permitAll()
                //他就不让启动，于是随便弄了个空matcher匹配链，走个过场
                .anyRequest()
                .authenticated();

        //handler
        http.exceptionHandling()
                //认证失败401
                .authenticationEntryPoint(authenticationEntryPoint)
                //权限不足403
                .accessDeniedHandler(accessDeniedHandler);

        //session registry
        http.sessionManagement()
                //最大并发会话量，就是sb腾讯视频同账号多登数量。我很大方 :2 我设置20个
                .maximumSessions(20)
                //其实这里不写也行，他默认就是这SessionRegistryImpl
                .sessionRegistry(sessionRegistry());
    }

    /**
     * Web静态资源过滤
     * 貌似完全不会被SpringSecurity拦截受理，比httpSecurity的ExpressionUrlXXXConfigurer更纯粹
     * 所以不要把/login /lougout /register放在这里ignore了，否则Spring Security相当于看不到该资源请求，
     * 因此就不会对/login做任何处理了。
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                //ant匹配，也可以用来忽略非静态资源
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/index.html",
                        "favicon.ico",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/captcha",
                        "/ws/**"
                );
    }
}
