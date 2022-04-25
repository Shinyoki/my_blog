package com.senko.framework.config.security.manager;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Api接口拦截
 *
 * 它的主要责任就是当访问一个url时返回这个url所需要的访问权限。
 * XXXSecurityMetadataSource  ==> Object instanceof XXX
 * {@link #getAttributes(Object)}中Object实际是 {@link org.springframework.security.web.FilterInvocation}
 * @author senko
 * @date 2022/4/25 22:34
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filter = (FilterInvocation) object;
        //TODO 过滤Url请求，判断用户是否具有权限
        return null;
    }

    /** 没有必要使用getAllConfigAttributes() */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //so we return null
        return null;
    }

    /** 记得改为true受理所有clazz */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
