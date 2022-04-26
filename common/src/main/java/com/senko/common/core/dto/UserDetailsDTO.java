package com.senko.common.core.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserDetails实现类：Authentication#Principal
 *
 * @author senko
 * @date 2022/4/26 16:19
 */
@Data
//链式设置
@Builder
public class UserDetailsDTO implements UserDetails {

    private static final long serialVersionUID = 1L;

    /** ===============UserDetails================ */
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户被禁用
     */
    private Boolean isDisabled;

    /**
     * 权限列表ROLE_XXX
     */
    private List<String> roles;

    /** ===============博客相关(应该封装成一个实体类，草率了)================ */

    /**
     * 用户Info id
     */
    private Integer userInfoId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像
     * 超链接
     */
    private String avatar;

    /**
     * 用户简介
     */
    private String intro;

    /**
     * 个人网站
     */
    private String webSite;

    /**
     * 登录方式
     * 1邮箱 2qq 3微博
     */
    private Integer loginType;

    /**
     * 点赞集合
     */
    private Set<Object> articleLikeSet;

    /**
     * 评论集合
     */
    private Set<Object> commentLikeSet;

    /**
     * 点赞说说集合
     */
    private Set<Object> talkLikeSet;

    /** ===============UserAgent============== */

    /**
     * 用户登录ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 使用的浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 最近的登录时间
     */
    private LocalDateTime lastLoginTime;

    //用户权限ROLE_XXX 2 SimpleGrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isDisabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
