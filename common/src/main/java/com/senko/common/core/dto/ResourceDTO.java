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
 * 资源DTO
 * @author senko
 * @date 2022/5/25 20:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("资源DTO")
public class ResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @ApiModelProperty("资源ID")
    private Integer id;

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String resourceName;

    /**
     * 资源操作路径
     */
    @ApiModelProperty("资源操作路径")
    private String url;

    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String requestMethod;

    /**
     * 是否禁用 0-否 1-是
     */
    @ApiModelProperty("是否禁用 0-否 1-是")
    private Integer isDisable;

    /**
     * 是否可以匿名访问 0-否 1-是
     */
    @ApiModelProperty("是否可以匿名访问 0-否 1-是")
    private Integer isAnonymous;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    // =========模块之下的子资源===========

    /**
     * 子资源权限列表
     */
    @ApiModelProperty("子权限列表")
    private List<ResourceDTO> children;
}
