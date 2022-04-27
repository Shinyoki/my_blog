package com.senko.system.service.impl;

import com.senko.common.core.dto.ResourceRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.RoleMapper;
import com.senko.common.core.entity.RoleEntity;
import com.senko.system.service.IRoleService;

import java.util.List;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 得到非空的，非匿名访问的操作路径
     * @return 非空的，非匿名访问的操作路径
     */
    @Override
    public List<ResourceRoleDTO> listOfNonAnonymousResourcesRoles() {
        return roleMapper.listOfNonAnonymousResourcesRoles();
    }

    /**
     * 通过UserInfoId得到相应用户的角色
     * @param userInfoId
     * @return
     */
    @Override
    public List<String> listRolesByUserInfoId(Integer userInfoId) {
        return roleMapper.listRolesByUserInfoId(userInfoId);
    }
}