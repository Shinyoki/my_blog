package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息VO
 *
 * @author senko
 * @date 2022/6/19 11:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "用户信息对象")
public class UserinfoVO {
    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickname", value = "昵称", dataType = "String", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 用户简介
     */
    @ApiModelProperty(name = "intro", value = "用户简介", dataType = "String")
    private String intro;

    /**
     * 用户个人网站
     */
    @ApiModelProperty(name = "webSite", value = "用户个人网站", dataType = "String")
    private String webSite;

}
