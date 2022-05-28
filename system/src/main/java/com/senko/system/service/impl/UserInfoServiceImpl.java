package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserDetailsDTO;
import com.senko.common.core.dto.UserOnlineDTO;
import com.senko.common.core.entity.UserRoleEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.UserIsDisableVO;
import com.senko.common.core.vo.UserRoleVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UserInfoMapper;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.system.service.IUserInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private SessionRegistry sessionRegistry;

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

    /**
     * 查询在线用户
     * @param conditionVO   条件
     * @return              在线用户 分页集合
     */
    @Override
    public PageResult<UserOnlineDTO> listUserOnline(ConditionVO conditionVO) {
//        sessionRegistry.getAllPrincipals().stream()
//                //过滤：存在一个或多个未过期的session的用户
//                .filter(principal -> sessionRegistry.getAllSessions(principal, false).size() > 0)
//                .forEach(principal ->{
//                    System.out.println("principal类型：" + principal.getClass());
//                    System.out.println("得到的在线用户" +JSON.toJSONString(principal));
//                });
        //从registry中提取在线的Principal用户
        List<UserOnlineDTO> userOnlineDTOList = sessionRegistry.getAllPrincipals().stream()
                //过滤：存在一个或多个未过期的session的用户
                .filter(principal -> sessionRegistry.getAllSessions(principal, false).size() > 0)
                //转换为UserDetails实现类
                //BeanCopyUtils转换的话lastLoginTime会得到空值。。。
                .map(principal -> JSON.parseObject(JSON.toJSONString(principal), UserOnlineDTO.class))
                .sorted(Comparator.comparing(UserOnlineDTO::getLastLoginTime))
                .collect(Collectors.toList());

        //分页
        int current = PageUtils.getLimitCurrent().intValue();
        int size = PageUtils.getSize().intValue();
        int to = userOnlineDTOList.size() - current > size ? current : userOnlineDTOList.size();
        List<UserOnlineDTO> userOnlineDTOS = userOnlineDTOList.subList(current, to);
        return new PageResult<>(userOnlineDTOList.size(),userOnlineDTOS);
    }

    /**
     * 踢在线用户下线
     * @param userInfoId    userInfoId
     */
    @Override
    public void kickOnlineUser(Integer userInfoId) {
        //筛选Principal
        Optional<Object> user = sessionRegistry.getAllPrincipals().stream()
                .filter(principal -> {
                    if (principal instanceof UserDetailsDTO) {
                        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) principal;
                        return userDetailsDTO.getUserInfoId().equals(userInfoId);
                    }
                    return false;
                })
                .findFirst();
        //提取对应principal的sessions，并通过SessionInformation让session过期
        user.ifPresent(userDetailsDTO -> sessionRegistry.getAllSessions(userDetailsDTO, false)
                .forEach(SessionInformation::expireNow));
    }
}
