package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 留言（弹幕）DTO
 *
 * @author senko
 * @date 2022/5/24 22:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("留言（弹幕）DTO")
public class MessageDTO implements Serializable {
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
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 留言信息
     */
    @ApiModelProperty("留言信息")
    private String messageContent;

    /**
     * 弹幕速度
     */
    @ApiModelProperty("弹幕速度")
    private Integer time;
}
