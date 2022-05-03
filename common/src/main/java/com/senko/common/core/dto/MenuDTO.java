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
 * 菜单DTO
 *
 * @author senko
 * @date 2022/5/3 14:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜单DTO")
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = -8176859017765230171L;

    /** ======= 和PO层一致的部分 ======= */

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Integer id;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 对应路径
     */
    @ApiModelProperty("对应路径")
    private String path;

    /**
     * 对应组件
     */
    @ApiModelProperty("对应组件")
    private String component;

    /**
     * 排序等级
     */
    @ApiModelProperty("排序等级")
    private Integer orderNum;

    /**
     * 是否被隐藏
     */
    @ApiModelProperty("是否隐藏")
    private Boolean isHidden;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /** ======= 和PO层不一致的部分 ======= */

    /**
     * 是否禁用
     */
    @ApiModelProperty("是否禁用")
    private Boolean isDisable;

    /**
     * 子菜单
     */
    @ApiModelProperty("子菜单")
    private List<MenuDTO> children;
}
