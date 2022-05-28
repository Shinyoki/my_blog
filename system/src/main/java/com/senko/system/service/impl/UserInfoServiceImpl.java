package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.entity.UserRoleEntity;
import com.senko.common.core.vo.UserIsDisableVO;
import com.senko.common.core.vo.UserRoleVO;
import com.senko.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UserInfoMapper;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.system.service.IUserInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息Service
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 更新用户的禁用状态
     * @param isDisableVO   用户信息id、禁用态
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserIsDisable(UserIsDisableVO isDisableVO) {
        UserInfoEntity userInfo = UserInfoEntity.builder()
                .id(isDisableVO.getId())
                .isDisable(isDisableVO.getIsDisable())
                .build();
        this.updateById(userInfo);
    }

    /**
     * 更新用户的角色列表
     * @param userRoleVO    用户id、用户名、角色id集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserRole(UserRoleVO userRoleVO) {
        //init data
        UserInfoEntity userInfo = UserInfoEntity.builder()
                .id(userRoleVO.getUserInfoId())
                .nickname(userRoleVO.getNickname())
                .build();
        userInfoMapper.updateById(userInfo);

        //修改roles 【先删除、再添加】
        //删除当前角色绑定的roles
        userRoleService.remove(new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userInfo.getId()));
        //添加
        List<UserRoleEntity> userRoleList = userRoleVO.getRoleIdList().stream()
                .map(roleId -> {
                    return UserRoleEntity.builder()
                            .userId(userInfo.getId())
                            .roleId(roleId)
                            .build();
                })
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
    }
}
