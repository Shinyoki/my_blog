package com.senko.framework.config.security.manager;

import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Api接口拦截，标志所需角色（动态权限）
 *
 * 它的主要责任就是当访问一个url时返回这个url所需要的访问权限。
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限
 *
 * XXXSecurityMetadataSource  ==> Object instanceof XXX
 * {@link #getAttributes(Object)}中Object实际是 {@link org.springframework.security.web.FilterInvocation}
 * @author senko
 * @date 2022/4/25 22:34
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    //角色service
    @Autowired
    private IRoleService roleService;

    //资源菜单权限缓存：防止多次重复读取数据库
    List<ResourceRoleDTO> resourcesRoles;

    /**
     * 加载资源
     */
    private void loadResourcesCache() {
        resourcesRoles = roleService.listOfNonAnonymousResourcesRoles();
    }

    /**
     * 清空资源：
     * 在ResourceService中对数据库进行修改操作后，要及时清空缓存
     */
    public void clearResourcesCache() {
        resourcesRoles = null;
    }


    /**
     * 指定该资源的权限
     * @param object    受保护对象，资源是URL网址时，则object为FilterInvocation
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取所有操作菜单和相关角色：非匿名访问
        if (StringUtils.isNull(resourcesRoles)) {
            loadResourcesCache();
        }
        //过滤器链：当前过的滤器
        FilterInvocation filter = (FilterInvocation) object;
        //获取请求类型
        String requestMethod = filter.getRequest().getMethod();
        //获取URL Path
        String requestURI = filter.getRequest().getRequestURI();

        /**
         * 将URL资源所对应的 RequestMethod & URL Path & ROLES 与
         */
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        //遍历相关角色菜单，找到当前菜单
        for (ResourceRoleDTO resourcesRole : resourcesRoles) {
            if (antPathMatcher.match(resourcesRole.getUrl(), requestURI) &&
                    requestMethod.equals(resourcesRole.getRequestMethod())) {
                //匹配到路径 & 方法一致，返回所需GrantAuthorities
                List<String> roles = resourcesRole.getRoles();

                if (CollectionUtils.isEmpty(roles)) {
                    //数据库没有 明确指定需要什么角色ROLE_XXX list
                    return SecurityConfig.createList("disable");
                } else {
                    //存在
                    String[] roleStrArray = resourcesRole.getRoles().stream()
                            .toArray(String[]::new);
                    return SecurityConfig.createList(roleStrArray);
                }
            }
        }
        //没有匹配到uri&method对应的roles，当作是匿名访问，于是返回null，此时匿名用户不具备 roles
        return null;
    }

    /** 没有必要使用getAllConfigAttributes() */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //so we return null
        return null;
    }

    /**
     * #getAttributes(Object object)
     * 受理URL请求则object.clazz == FilterInvocation.class
     * 又或者是MethodInvocation.class
     * 记得改为true受理所有clazz
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
