package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.entity.UserAuthEntity;

import java.util.Map;

/**
 * 
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface IUserAuthService extends IService<UserAuthEntity> {

    /**
     * 通过用户名得到用户（带密码
     * id userInfoId username password loginType
     * @param username
     * @return
     */
    UserAuthEntity getByUsername(String username);
}

