package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色DTO
 * @author senko
 * @date 2022/5/26 9:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("角色DTO")
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = -8123818479655165588L;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 角色标签 如：admin,user
     */
    @ApiModelProperty("角色标签 如：admin,user")
    private String roleLabel;

    /**
     * 角色创建时间
     */
    @ApiModelProperty("角色创建时间")
    private LocalDateTime createTime;

    /**
     * 是否被禁用 0:否 1:是
     */
    @ApiModelProperty("是否被禁用 0:否 1:是")
    private Integer isDisable;

    // ============相关内容===========

    /**
     * 资源ID集合: tb_role_resource
     */
    @ApiModelProperty("资源ID集合: tb_role_resource")
    private List<Integer> resourceIdList;

    /**
     * 菜单ID集合: tb_role_menu
     */
    @ApiModelProperty("菜单ID集合: tb_role_menu")
    private List<Integer> menuIdList;
}
