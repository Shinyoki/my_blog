package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface IRoleService extends IService<RoleEntity> {

    /**
     * 得到非空的，非匿名访问的 操作路径、相关请求、相关角色
     * @return 非空的，非匿名访问的 操作路径、相关请求、相关角色
     */
    List<ResourceRoleDTO> listOfNonAnonymousResourcesRoles();

    /**
     * 通过UserInfoId得到相应用户的角色
     * @param userInfoId
     * @return
     */
    List<String> listRolesByUserInfoId(Integer userInfoId);
}

