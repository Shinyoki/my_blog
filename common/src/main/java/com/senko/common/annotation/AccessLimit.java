package com.senko.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问限流注解：依赖redis实现
 * @author senko
 * @date 2022/5/15 16:58
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 限制时间（秒）
     */
    int seconds();

    /**
     * 单位时间内最大请求次数（秒）
     */
    int maxCount();
}
