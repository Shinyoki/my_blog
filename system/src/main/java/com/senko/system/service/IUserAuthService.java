package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.vo.*;

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


    /**
     * QQ登录
     * @param loginVO        登录信息
     * @return               登录后的用户
     */
    UserLoginInfoDTO qqLogin(QQLoginVO loginVO);

    /**
     * Github登录
     * @param githubVO        登录信息
     * @return               登录后的用户
     */
    UserLoginInfoDTO githubLogin(GithubVO githubVO);

    /**
     * 发送邮箱验证码
     * @param username  邮箱地址
     */
    void sendEmailValidCode(String username);

    /**
     * 注册用户
     * @param userVO    用户信息
     */
    void doRegister(UserVO userVO);
}

