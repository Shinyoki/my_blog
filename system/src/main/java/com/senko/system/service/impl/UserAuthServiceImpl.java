package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.system.service.IUserAuthService;


@Service("userAuthService")
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements IUserAuthService {

    @Override
    public void run() {
        System.out.println("==================");
    }
}