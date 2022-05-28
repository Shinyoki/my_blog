package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.core.vo.UserIsDisableVO;
import com.senko.common.core.vo.UserRoleVO;

import java.util.Map;

/**
 * 用户信息Service
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface IUserInfoService extends IService<UserInfoEntity> {


    /**
     * 更新用户的禁用状态
     * @param isDisableVO   用户信息id、禁用态
     */
    void updateUserIsDisable(UserIsDisableVO isDisableVO);

    /**
     * 更新用户的角色列表
     * @param userRoleVO    用户id、用户名、角色id集合
     */
    void updateUserRole(UserRoleVO userRoleVO);
}

