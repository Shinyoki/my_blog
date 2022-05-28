package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色VO
 *
 * @author senko
 * @date 2022/5/28 14:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户角色VO")
public class UserRoleVO implements Serializable {
    private static final long serialVersionUID = -8691758490111374444L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空")
    private Integer userInfoId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    /**
     * 角色ID 集合
     */
    @ApiModelProperty("角色ID 集合")
    @NotEmpty(message = "角色ID 集合不能为空")
    private List<Integer> roleIdList;
}
