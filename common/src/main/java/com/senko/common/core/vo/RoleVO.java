package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 角色VO
 * @author senko
 * @date 2022/5/27 10:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("角色VO")
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名", required = true)
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /**
     * 资源标签
     */
    @ApiModelProperty(value = "资源标签",required = true)
    @NotBlank(message = "资源标签不能为空")
    private String roleLabel;

    /**
     * 绑定的资源ID 集合
     */
    @ApiModelProperty("绑定的资源ID 集合")
    private List<Integer> resourceIdList;

    /**
     * 绑定的菜单列表
     */
    @ApiModelProperty("绑定的菜单列表")
    private List<Integer> menuIdList;
}
