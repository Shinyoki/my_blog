package com.senko.framework.web.service;

import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.UserInfoMapper;
import com.senko.system.service.IRoleService;
import com.senko.system.service.IUserAuthService;
import com.senko.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserDetailsService
 *
 * @author senko
 * @date 2022/4/27 18:27
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //TODO userDetailsService

    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 账号获取
     *
     * @param username
     * @return 得到的用户
     * @throws UsernameNotFoundException  可以不用抛出该异常，以自定义异常代替，记得用ControllerAdvice捕获
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserAuthEntity userAuth = userAuthService.getByUsername(username);
        if (StringUtils.isNull(userAuth)) {
            //用户为空
            throw new UsernameNotFoundException("用户不存在！");
        }
        return buildUserDetailDTO(userAuth);
    }

    /**
     * 修饰信息
     * @param userAuth
     * @return
     */
    private UserDetails buildUserDetailDTO(UserAuthEntity userAuth) {
        //用户个人信息
        UserInfoEntity userInfo = userInfoService.getById(userAuth.getUserInfoId());
        //用户角色：设计的是 user_role表与user_info相关联
        List<String> roles = roleService.listRolesByUserInfoId(userInfo.getId());
        //TODO UserDetailsImpl Set<Object> ...



        return null;
    }
}
