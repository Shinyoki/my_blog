package com.senko.framework.strategy.impl.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.senko.common.core.dto.SocialTokenDTO;
import com.senko.common.core.dto.SocialUserInfoDTO;
import com.senko.common.core.dto.UserDetailsDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.core.entity.UserRoleEntity;
import com.senko.common.enums.UserTypeEnum;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.framework.strategy.LoginStrategy;
import com.senko.framework.web.service.UserDetailsServiceImpl;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.system.mapper.UserInfoMapper;
import com.senko.system.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 抽象登录实现
 * @author senko
 * @date 2022/7/12 6:34
 */
@Service
public abstract class AbstractSocialLoginStrategyImpl implements LoginStrategy {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserLoginInfoDTO login(String data) {
        // UserDetails
        UserDetailsDTO userDetails = null;
        // 提取TOKEN信息
        SocialTokenDTO socialToken = getSocialToken(data);

        // 判断是否注册过了
        UserAuthEntity userAuth = getUserAuth(socialToken);
        if (Objects.isNull(userAuth)) {
            // 还未注册
            userDetails = registerUserDetails(socialToken);
        } else {
            // 保存登录信息
            userDetails = getUserDetails(userAuth);
        }

        // 判断是否被禁用
        if (userDetails.getIsDisabled()) {
            throw new ServiceException("该账号已被禁用！");
        }

        // 保存登录信息到SecurityContextHolder中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(socialToken.getOpenId(), socialToken.getAccessToken());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 返回不带密码的UserInfo
        return BeanCopyUtils.copyObject(userDetails, UserLoginInfoDTO.class);
    }

    /**
     * 注册用户
     */
    private UserDetailsDTO registerUserDetails(SocialTokenDTO socialToken) {
        // 第三方用户信息
        SocialUserInfoDTO socialUserInfo = getSocialUserInfo(socialToken);
        // 保存UserInfoPO
        UserInfoEntity userInfoEntity = UserInfoEntity.builder()
                .nickname(socialUserInfo.getNickname())
                .avatar(socialUserInfo.getAvatar())
                .build();
        userInfoMapper.insert(userInfoEntity);
        // 保存UserAuthPO
        String ipAddress = IpUtils.getIpAddressFromRequest(ServletUtils.getRequest());
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAuthEntity userAuthEntity = UserAuthEntity.builder()
                .userInfoId(userInfoEntity.getId())
                // 第三方登录的账号为accessID
                .username(socialToken.getOpenId())
                // 第三方登录的密码为accessToken
                .password(socialToken.getAccessToken())
                .loginType(socialToken.getLoginType())
                .lastLoginTime(LocalDateTime.now())
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .build();
        userAuthMapper.insert(userAuthEntity);
        // 绑定Role
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .userId(userInfoEntity.getId())
                .roleId(UserTypeEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRoleEntity);

        return (UserDetailsDTO) userDetailsService.buildUserDetailDTO(userAuthEntity);
    }

    /**
     * 保存登录信息
     */
    private UserDetailsDTO getUserDetails(UserAuthEntity userAuth) {
        String ipAddress = IpUtils.getIpAddressFromRequest(ServletUtils.getRequest());
        String ipSource = IpUtils.getIpSource(ipAddress);
        // 更新数据库
        userAuthMapper.update(new UserAuthEntity(), new LambdaUpdateWrapper<UserAuthEntity>()
                .set(UserAuthEntity::getLastLoginTime, LocalDateTime.now())
                .set(UserAuthEntity::getIpSource, ipSource)
                .set(UserAuthEntity::getIpAddress, ipAddress)
                .eq(UserAuthEntity::getId, userAuth.getId()));

        return (UserDetailsDTO) userDetailsService.buildUserDetailDTO(userAuth);
    }

    /**
     * 根据信息查询数据库中有没有注册过
     */
    private UserAuthEntity getUserAuth(SocialTokenDTO socialToken) {
        return userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuthEntity>()
                // username == openId
                .eq(Objects.nonNull(socialToken.getOpenId()), UserAuthEntity::getUsername, socialToken.getOpenId())
                // loginType == socialLoginType
                .eq(Objects.nonNull(socialToken.getLoginType()), UserAuthEntity::getLoginType, socialToken.getLoginType()));
    }

    /**
     * 从str中提取token信息
     */
    protected abstract SocialTokenDTO getSocialToken(String str);

    /**
     * 根据TOKEN获取第三方平台的用户信息
     */
    protected abstract SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialToken);
}
