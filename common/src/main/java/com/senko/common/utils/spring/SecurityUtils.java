package com.senko.common.utils.spring;

import com.senko.common.core.dto.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 利用SecurityContext得到client端信息
 *
 * @author senko
 * @date 2022/4/26 16:12
 */
public class SecurityUtils {

    /**
     * 返回Context
     * @return
     */
    public static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 返回Authentication
     * @return
     */
    public static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     *
     * @return Principal ==> UserDetails实现类
     */
    public static UserDetailsDTO getLoginUser() {
        return (UserDetailsDTO) getAuthentication().getPrincipal();
    }

}
