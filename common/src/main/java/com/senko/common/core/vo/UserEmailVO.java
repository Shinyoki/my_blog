package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 绑定用户邮箱VO
 * @author senko
 * @date 2022/7/14 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("绑定用户邮箱VO")
public class UserEmailVO {

    /**
     * 绑定的邮箱
     */
    @NotBlank(message = "邮箱地址不能为空")
    @Email(message = "邮箱地址格式不正确")
    @ApiModelProperty(name = "email", value = "邮箱地址", required = true, dataType = "String")
    private String email;

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "邮箱验证码不能为空")
    @ApiModelProperty(name = "code", value = "邮箱验证码", required = true, dataType = "String")
    private String code;

}
