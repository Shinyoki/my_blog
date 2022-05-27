package com.senko.system.mapper;

import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.dto.RoleDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询角色列表
     * @param conditionVO       条件
     * @return                  角色  分页集合
     */
    List<RoleDTO> listRoles(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO conditionVO);
}
