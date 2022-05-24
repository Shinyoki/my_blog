package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台留言（弹幕）DTO
 *
 * @author senko
 * @date 2022/5/24 22:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台留言（弹幕）DTO")
public class MessageBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 留言ID
     */
    @ApiModelProperty("留言ID")
    private Integer id;

    /**
     * 发言人昵称
     */
    @ApiModelProperty("发言人昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 留言内容
     */
    @ApiModelProperty("留言内容")
    private String messageContent;

    /**
     * 是否审核通过 0:审核中、1：正常
     */
    @ApiModelProperty("是否审核通过 0:审核中、1：正常")
    private Integer isReview;

    /**
     * 发言时间
     */
    @ApiModelProperty("留言时间")
    private LocalDateTime createTime;

    // ==============后台信息===========

    /**
     * IP地址
     */
    @ApiModelProperty("IP地址")
    private String ipAddress;

    /**
     * ip所在区域
     */
    @ApiModelProperty("ip所在区域")
    private String ipSource;
}
