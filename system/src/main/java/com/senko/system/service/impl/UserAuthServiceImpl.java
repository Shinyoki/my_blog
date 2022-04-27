package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.system.service.IUserAuthService;


@Service("userAuthService")
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements IUserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    /**
     * 通过用户名得到用户（只有部分信息，用于UserDetailsService
     * @param username
     * @return
     */
    @Override
    public UserAuthEntity getByUsername(String username) {
        //只获取
         return userAuthMapper.selectOne(
                    new LambdaQueryWrapper<UserAuthEntity>()
                            //select
                            .select(UserAuthEntity::getId, UserAuthEntity::getUserInfoId, UserAuthEntity::getUsername, UserAuthEntity::getPassword, UserAuthEntity::getLoginType)
                            //where
                           .eq(UserAuthEntity::getUsername, username)
         );
    }
}