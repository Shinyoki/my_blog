package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Github Code
 * @author senko
 * @date 2022/7/12 14:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Github Code")
public class GithubVO {

    /**
     * Github Code
     */
    @NotBlank(message = "认证码不能为空")
    @ApiModelProperty(value = "Github Code", required = true, dataType = "String")
    private String code;

}
