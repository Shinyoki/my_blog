package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.RoleResourceMapper;
import com.senko.common.core.entity.RoleResourceEntity;
import com.senko.system.service.IRoleResourceService;


@Service("roleResourceService")
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceEntity> implements IRoleResourceService {

}