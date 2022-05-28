package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 后台用户信息DTO
 *
 * @author senko
 * @date 2022/5/28 8:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("后台用户信息DTO")
public class UserBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 后台用户ID
     */
    @ApiModelProperty("后台用户ID")
    private Integer id;

    /**
     * 登录用户信息ID
     */
    @ApiModelProperty("登录用户信息ID")
    private Integer userInfoId;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 登录方式 1:邮箱 2: qq 3:微博
     */
    @ApiModelProperty("登录方式 1:邮箱 2: qq 3:微博")
    private Integer loginType;

    /**
     * 登录IP地址
     */
    @ApiModelProperty("登录IP地址")
    private String ipAddress;

    /**
     * 登录所在区域
     */
    @ApiModelProperty("登录所在区域")
    private String ipSource;

    /**
     * 禁用状态 0:启用 1:禁用
     */
    @ApiModelProperty("禁用状态 0:启用 1:禁用")
    private Integer isDisable;

    /**
     * 后台用户创建时间
     */
    @ApiModelProperty("后台用户创建时间")
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 用户角色集合
     */
    @ApiModelProperty("用户角色集合")
    private List<UserRoleDTO> roleList;
}
