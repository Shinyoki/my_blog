package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户登录信息，不带密码，其他
 *
 * @author senko
 * @date 2022/4/26 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginInfoDTO  {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

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
     * 最近的登录时间
     */
    private LocalDateTime lastLoginTime;
}
