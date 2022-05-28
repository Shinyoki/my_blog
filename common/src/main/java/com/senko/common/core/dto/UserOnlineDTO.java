package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 在线用户DTO
 *
 * @author senko
 * @date 2022/5/28 20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("在线用户DTO")
public class UserOnlineDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 用户信息ID
     */
    @ApiModelProperty("用户信息ID")
    private Integer userInfoId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * IP地址
     */
    @ApiModelProperty("IP地址")
    private String ipAddress;

    /**
     * IP来源
     */
    @ApiModelProperty("IP来源")
    private String ipSource;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    private Long lastLoginTime;

    // ===== userAgent =====

    /**
     * 浏览器类型
     */
    @ApiModelProperty("浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String os;


}
