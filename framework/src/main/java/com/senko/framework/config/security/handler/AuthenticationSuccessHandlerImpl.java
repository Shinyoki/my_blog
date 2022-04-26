package com.senko.framework.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.UserDetailsDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.system.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证Authorized成功处理器
 *
 * 向前端响应用户信息、更新数据库
 * @author senko
 * @date 2022/4/25 10:21
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private IUserAuthService userAuthService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
    ) throws IOException, ServletException {
        //TODO 登录成功处理器：响应前端UserDTO，更新数据库用户信息、向用户邮箱发送登录提示等
        //shift，你知道吗，tnnd 原来没通过认证的Authentication没有Principal用户，他是null的
        //所以整了个强制非NULL的复制
        UserDetailsDTO loginUser = SecurityUtils.getLoginUser();
        //spring和common的BeanUtils都可以支持跨类复制，通过反射将同名属性复制，所以整了个没有密码版本的DTO
        UserLoginInfoDTO loginInfo = BeanCopyUtils.copyObject(loginUser, UserLoginInfoDTO.class);
        /**
         * 返回登录数据
         * 不是带有密码的UserDetails
         */
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success(loginInfo)));
        /**
         * 更新数据库
         * 数据库的user_auth表就需要带用户的密码了
         */
        updateUserInfo(loginUser);
    }

    /**
     * 异步虽好，但要注意失效场景，不要在异步里调用新的异步，除非用Factory重新获取下增强类
     */
    @Async
    public void updateUserInfo(UserDetailsDTO userDetails) {
        UserAuthEntity userAuth = UserAuthEntity.builder()
                .id(userDetails.getId())
                .ipAddress(userDetails.getIpAddress())
                .ipSource(userDetails.getIpSource())
                //上一次的登录时间 会在 Authentication认证成功时设置
                .lastLoginTime(userDetails.getLastLoginTime())
                .build();
        userAuthService.updateById(userAuth);
    }

}
