package com.senko.framework.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.constants.CommonConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.dto.RoleDTO;
import com.senko.common.core.dto.UserRoleDTO;
import com.senko.common.core.entity.RoleMenuEntity;
import com.senko.common.core.entity.RoleResourceEntity;
import com.senko.common.core.entity.UserRoleEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.RoleVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.framework.config.security.manager.FilterInvocationSecurityMetadataSourceImpl;
import com.senko.system.mapper.*;
import com.senko.system.service.IRoleMenuService;
import com.senko.system.service.IRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.core.entity.RoleEntity;
import com.senko.system.service.IRoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色服务
 */
@Service("roleService")
public class SysRoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private IRoleResourceService roleResourceService;

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

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

    /**
     * 查询角色列表
     * @param conditionVO       条件
     * @return                  角色  分页集合
     */
    @Override
    public PageResult<RoleDTO> listRoles(ConditionVO conditionVO) {
        Long count = roleMapper.selectCount(new LambdaQueryWrapper<RoleEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), RoleEntity::getRoleName, conditionVO.getKeywords()));
        if (count == 0) {
            return new PageResult<>();
        }

        List<RoleDTO> roleDTOList = roleMapper.listRoles(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<RoleDTO>(count.intValue(), roleDTOList);
    }

    /**
     * 删除角色 如果存在与角色绑定的用户，则失败
     * @param roleIdList       角色ID 集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoles(List<Integer> roleIdList) {
        Long count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRoleEntity>()
                .in(UserRoleEntity::getRoleId, roleIdList));
        if (count > 0) {
            throw new ServiceException("有用户绑定了所选角色，因此删除失败！");
        }
        //删除绑定的role-menu
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenuEntity>()
                .in(RoleMenuEntity::getRoleId, roleIdList));
        //删除绑定的role-resource
        roleResourceMapper.delete(new LambdaQueryWrapper<RoleResourceEntity>()
                .in(RoleResourceEntity::getRoleId, roleIdList));
        //删除role
        roleMapper.deleteBatchIds(roleIdList);
    }

    /**
     * 新增或修改角色
     * @param roleVO    角色VO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateRole(RoleVO roleVO) {
        //判重：角色标签可以是多个，但角色名只能有一个
        Long count = roleMapper.selectCount(new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getRoleName, roleVO.getRoleName()));
        if (count > 0 && Objects.isNull(roleVO.getId())) {
            //存在用户名，但是没有传入ID，此时希望添加而非修改
            throw new ServiceException("该角色名已存在！");
        }

        //存储或更新Role
        RoleEntity role = RoleEntity.builder()
                .id(roleVO.getId())
                .roleName(roleVO.getRoleName())
                .roleLabel(roleVO.getRoleLabel())
                .isDisable(CommonConstants.FALSE)
                .build();
        this.saveOrUpdate(role);
        //经过上面的saveOrUpdate操作后，role会被回调函数处理，如果roleVo.getId() == null,
        //MP会在数据库中生成并重新查询，把新的id赋值回来给role

        //存储或更新role-menu
        if (CollectionUtils.isNotEmpty(roleVO.getMenuIdList())) {
            //如果roleVO.getId nonNull，先删除在修改
            if (Objects.nonNull(roleVO.getId())) {
                roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenuEntity>()
                        .eq(RoleMenuEntity::getRoleId, roleVO.getId()));
            }

            List<RoleMenuEntity> roleMenuList = roleVO.getMenuIdList().stream()
                    .map(menuId -> {
                        return RoleMenuEntity.builder()
                                .menuId(menuId)
                                .roleId(role.getId())
                                .build();
                    })
                    .collect(Collectors.toList());

            roleMenuService.saveBatch(roleMenuList);
        }

        //存储或更新role-resource
        if (CollectionUtils.isNotEmpty(roleVO.getResourceIdList())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleResourceService.remove(new LambdaQueryWrapper<RoleResourceEntity>()
                        .eq(RoleResourceEntity::getRoleId, roleVO.getId()));
            }

            List<RoleResourceEntity> roleResourceList = roleVO.getResourceIdList().stream()
                    .map(resourceId -> {
                        return RoleResourceEntity.builder()
                                .roleId(role.getId())
                                .resourceId(resourceId)
                                .build();
                    })
                    .collect(Collectors.toList());

            roleResourceService.saveBatch(roleResourceList);
            //更新角色资源权限缓存
            filterInvocationSecurityMetadataSource.clearResourcesCache();
        }
    }

    /**
     * 查询 用户角色 集合
     * @return      用户角色 集合
     */
    @Override
    public List<UserRoleDTO> listUserRoles() {
        List<RoleEntity> roles = roleMapper.selectList(new LambdaQueryWrapper<RoleEntity>()
                .select(RoleEntity::getId, RoleEntity::getRoleName));

        return BeanCopyUtils.copyList(roles, UserRoleDTO.class);
    }
}
