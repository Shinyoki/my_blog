package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单VO
 * @author senko
 * @date 2022/5/25 20:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("菜单VO")
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Integer id;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Integer parentId;

    /**
     * 是否隐藏 0：false， 1：true
     */
    @ApiModelProperty("是否隐藏 0：false， 1：true")
    private Integer isHidden;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    @NotEmpty(message = "菜单名不能为空")
    private String name;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", required = true)
    @NotEmpty(message = "菜单图标不能为空")
    private String icon;

    /**
     * 菜单组件
     */
    @ApiModelProperty(value = "菜单组件", required = true)
    @NotEmpty(message = "菜单组件")
    private String component;

    /**
     * 路由路径
     */
    @ApiModelProperty(value = "菜单路径", required = true)
    @NotEmpty(message = "路径不能为空")
    private String path;

    /**
     * 排序
     */
    @ApiModelProperty(value = "菜单排序", required = true)
    @NotNull(message = "菜单排序不能为空")
    private Integer orderNum;


}
