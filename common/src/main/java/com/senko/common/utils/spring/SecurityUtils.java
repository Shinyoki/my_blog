package com.senko.common.utils.spring;

import com.senko.common.core.dto.UserDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

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
        Object principal = getAuthentication().getPrincipal();
        LOGGER.info("principal的类为：{}", principal.getClass().getName());
        LOGGER.info("principal#toString: {}",principal.toString());
        return (UserDetailsDTO) principal;
    }

}
