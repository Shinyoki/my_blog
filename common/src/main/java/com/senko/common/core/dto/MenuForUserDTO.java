package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * MenuDTO的再次简化版本，省略掉一些不必要的属性
 *
 * 在结构上保持与Vue route一致，方便直接将前端接受的结果直接作为路由对象使用
 * 用于给传给前端确定当前登录用户的可访菜单
 * {@link MenuDTO}
 * @author senko
 * @date 2022/5/3 15:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("用户可访菜单DTO")
public class MenuForUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /** ======== 和MenuEntity一致的字段 ======== */

    /**
     * 菜单名
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单路径
     */
    @ApiModelProperty("菜单路径")
    private String path;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 对应组件
     */
    @ApiModelProperty("对应组件")
    private String component;

    /**
     * 是否隐藏
     */
    @ApiModelProperty("是否隐藏")
    private Boolean hidden;

    /** ========== 和MenuEntity不一致的字段 ======== */

    /**
     * 子菜单
     */
    @ApiModelProperty("子菜单")
    private List<MenuForUserDTO> children;
}
