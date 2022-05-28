package com.senko.framework.config.security.manager;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 访问权限管理器
 *
 *
 * 权限相关，访问认证链的最后一环，抛出异常就是拒绝，否则单纯return即可
 * 最终的访问授权是否通过是由AccessDecisionManager进行决策
 * @author senko
 * @date 2022/4/25 21:50
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    /**
     * 根据得到的authentication用户，判定该用户是否具有 collection权限集合的所需权限之一
     * @param authentication        用户信息：Authorities权限roles，Credentials密码，Details用户Details、Principal用户主体
     * @param object
     * @param configAttributes      访问资源所需要的权限 集合
     * @throws AccessDeniedException                访问拒绝
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException,
            InsufficientAuthenticationException {

        //提取 用户 的所有权限转化为ROLE_集合，streams不允许操作流以外的属性
        List<String> roles = authentication.getAuthorities()
                .stream()
                //将Collection的元素转换为 <? extends GrantedAuthority>类型
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        //比对 所需权限
        for (ConfigAttribute attribute : configAttributes) {
            if (roles.contains(attribute.getAttribute())) {
                return;
            }
        }

        //无法找到相应权限，一眼顶针，鉴定无权
        throw new AccessDeniedException("用户没有操作权限");
    }

    /**
     * 设置 访问决定管理器 的受理范围，都该为true即可
     * @param attribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
