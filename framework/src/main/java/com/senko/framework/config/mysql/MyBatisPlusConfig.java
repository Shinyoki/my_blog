package com.senko.framework.config.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MP配置类
 * @author senko
 * @date 2022/4/24 19:24
 */
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    /** 添加分页插件，Mybatis的插件就是Interceptor */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //MP拦截器，加入 分页拦截器
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return mpInterceptor;
    }

}
