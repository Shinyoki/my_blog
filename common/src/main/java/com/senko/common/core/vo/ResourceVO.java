package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 资源、表单资源VO
 *
 * @author senko
 * @date 2022/5/25 22:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("资源、表单资源VO")
public class ResourceVO implements Serializable {
    private static final long serialVersionUID = -8246578776825271731L;
    //TODO 资源VO

    /**
     * 资源ID
     */
    @ApiModelProperty("资源ID")
    private Integer id;

    /**
     * 资源名称
     */
    @NotEmpty(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称",required = true)
    private String resourceName;

    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    private String url;

    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String requestMethod;

    /**
     * 父模块ID
     */
    @ApiModelProperty("父模块ID")
    private Integer parentId;

    /**
     * 是否允许匿名访问 0-否 1-是
     */
    @ApiModelProperty("是否允许匿名访问 0-否 1-是")
    private Integer isAnonymous;
}
