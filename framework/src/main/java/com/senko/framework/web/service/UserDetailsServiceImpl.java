package com.senko.framework.web.service;

import cn.hutool.http.useragent.UserAgent;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.dto.UserDetailsDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.service.IRoleService;
import com.senko.system.service.IUserAuthService;
import com.senko.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

/**
 * UserDetailsService
 *
 * @author senko
 * @date 2022/4/27 18:27
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private RedisHandler redisHandler;


    /**
     * 账号获取
     *
     * @param username
     * @return 得到的用户
     * @throws UsernameNotFoundException  可以不用抛出该异常，以自定义异常代替，记得用ControllerAdvice捕获
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //从数据库中提取的信息不全id userInfoId username password loginType，需要修饰
        UserAuthEntity userAuth = userAuthService.getByUsername(username);
        if (StringUtils.isNull(userAuth)) {
            //用户为空
            throw new UsernameNotFoundException("用户不存在！");
        }
        //修饰userAuth
        return buildUserDetailDTO(userAuth);
    }

    /**
     * 修饰信息
     * @param userAuth
     * @return
     */
    public UserDetails buildUserDetailDTO(UserAuthEntity userAuth) {
        //用户 个人信息
        UserInfoEntity userInfo = userInfoService.getById(userAuth.getUserInfoId());
        //用户 角色：设计的是 user_auth仅用于验证密码、user_info作为真实用户
        List<String> roles = roleService.listRolesByUserInfoId(userInfo.getId());
        //用户 文章、说说、点赞 信息
        Set<Object> articleLikeSet = redisHandler.sMembers(RedisConstants.ARTICLE_USER_LIKE + userInfo.getId());
        Set<Object> talkLikeSet = redisHandler.sMembers(RedisConstants.TALK_USER_LIKE + userInfo.getId());
        Set<Object> commentLikeSet = redisHandler.sMembers(RedisConstants.COMMENT_USER_LIKE + userInfo.getId());
        //Ip地址、地理位置、UserAgent
        String ipAddress = IpUtils.getIpAddressFromRequest(ServletUtils.getRequest());
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = ServletUtils.getUserAgent();

        //修饰 UserDetails细节
        return UserDetailsDTO.builder()
                //UserAuth Table能提取到的部分有效信息 以及 UserDetails相关
                .id(userAuth.getId())
                .userInfoId(userAuth.getUserInfoId())
                .username(userAuth.getUsername())
                .password(userAuth.getPassword())
                .roles(roles)
                .loginType(userAuth.getLoginType())
                //点赞记录
                .articleLikeSet(articleLikeSet)
                .talkLikeSet(talkLikeSet)
                .commentLikeSet(commentLikeSet)
                //ip地址、地理信息、UserAgent
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOs().getName())
                //老数据
                .email(userInfo.getEmail())
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .isDisabled(userInfo.getIsDisable() == 1)
                //更新登录时间
                .lastLoginTime(LocalDateTime.now(ZoneId.of("Asia/Shanghai")))
                .build();
    }
}
