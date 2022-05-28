package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户禁用状态VO
 *
 * @author senko
 * @date 2022/5/28 13:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("用户禁用状态VO")
public class UserIsDisableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户信息ID TB_USER_INFO
     */
    @ApiModelProperty("用户信息ID")
    @NotNull(message = "用户信息ID不能为空")
    private Integer id;

    /**
     * 用户禁用状态 0：未禁用 1：已禁用
     */
    @ApiModelProperty("用户禁用状态 0：未禁用 1：已禁用")
    @NotNull(message = "用户禁用状态不能为空")
    private Integer isDisable;
}
