package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台友链DTO
 *
 * @author senko
 * @date 2022/6/17 19:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "后台友链DTO")
public class FriendLinkDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 友链ID
     */
    @ApiModelProperty(value = "友链ID")
    private Integer id;

    /**
     * 友链名称
     */
    @ApiModelProperty(value = "友链名称")
    private String linkName;

    /**
     * 友链头像
     */
    @ApiModelProperty(value = "友链头像")
    private String linkAvatar;

    /**
     * 友链地址
     */
    @ApiModelProperty(value = "友链地址")
    private String linkAddress;

    /**
     * 友链介绍
     */
    @ApiModelProperty(value = "友链介绍")
    private String linkIntro;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
