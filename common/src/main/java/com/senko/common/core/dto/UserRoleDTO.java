package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户角色DTO
 *
 * @author senko
 * @date 2022/5/28 8:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户角色DTO")
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;
}
