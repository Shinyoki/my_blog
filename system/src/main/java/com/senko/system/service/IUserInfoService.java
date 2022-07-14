package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserOnlineDTO;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.core.vo.*;

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

    /**
     * 查询在线用户
     * @param conditionVO   条件
     * @return              在线用户 分页集合
     */
    PageResult<UserOnlineDTO> listUserOnline(ConditionVO conditionVO);

    /**
     * 踢在线用户下线
     * @param userInfoId    userInfoId
     */
    void kickOnlineUser(Integer userInfoId);

    /**
     * 更新用户信息
     * @param userInfoVO    用户信息
     */
    void updateUserInfo(UserinfoVO userInfoVO);

    /**
     * 绑定用户邮箱
     * @param userEmailVO  邮箱 验证码
     */
    void bindUserEmail(UserEmailVO userEmailVO);
}

