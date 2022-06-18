package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 页面DTO
 *
 * @author senko
 * @date 2022/6/18 7:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "页面DTO")
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页面id
     */
    @ApiModelProperty("页面id")
    private Integer id;

    /**
     * 页面名
     */
    @ApiModelProperty("页面名")
    private String pageName;

    /**
     * 页面标签
     */
    @ApiModelProperty("页面标签")
    private String pageLabel;

    /**
     * 页面封面
     */
    @ApiModelProperty("页面封面")
    private String pageCover;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
