package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.UserPasswordVO;

/**
 * 后台用户模块
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

    /**
     * 查询后台用户 分页集合
     * @param conditionVO       条件（用户名、登陆类型）
     * @return                  后台用户 分页集合
     */
    PageResult<UserBackDTO> listUserBack(ConditionVO conditionVO);

    /**
     * 更新用户密码
     * @param userPasswordVO    用户id、用户名、密码
     */
    void updateUserPassword(UserPasswordVO userPasswordVO);
}

