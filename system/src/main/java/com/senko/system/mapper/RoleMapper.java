package com.senko.system.mapper;

import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 得到非空的，非匿名访问的操作路径
     * @return 非空的，非匿名访问的操作路径
     */
    List<ResourceRoleDTO> listOfNonAnonymousResourcesRoles();

    List<String> listRolesByUserInfoId(Integer id);
}
