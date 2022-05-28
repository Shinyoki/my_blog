package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.dto.RoleDTO;
import com.senko.common.core.dto.UserRoleDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.entity.RoleEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.RoleVO;

import java.util.List;
import java.util.Map;

/**
 * 角色Serivce
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

    /**
     * 查询角色列表
     * @param conditionVO       条件
     * @return                  角色  分页集合
     */
    PageResult<RoleDTO> listRoles(ConditionVO conditionVO);


    /**
     * 删除角色 如果存在与角色绑定的用户，则失败
     * @param roleIdList       角色ID 集合
     */
    void deleteRoles(List<Integer> roleIdList);

    /**
     * 新增或修改角色
     * @param roleVO    角色VO
     */
    void saveOrUpdateRole(RoleVO roleVO);

    /**
     * 查询 用户角色 集合
     * @return      用户角色 集合
     */
    List<UserRoleDTO> listUserRoles();
}

