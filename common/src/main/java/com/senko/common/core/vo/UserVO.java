package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册用户VO
 * @author senko
 * @date 2022/7/14 13:48
 */
@Data
@Builder
@ApiModel(description = "注册用户VO")
public class UserVO {

    /**
     * 用户名
     */
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(name = "username", value = "用户名", required = true, dataType = "String")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(name = "code", value = "邮箱验证码", required = true, dataType = "String")
    private String code;

}
