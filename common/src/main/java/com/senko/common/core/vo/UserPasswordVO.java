package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户密码VO
 * @author senko
 * @date 2022/6/19 11:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "用户密码VO")
public class UserPasswordVO {
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty(name = "oldPassword", value = "旧密码", dataType = "String", required = true)
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(name = "newPassword", value = "新密码", dataType = "String", required = true)
    private String newPassword;
}
