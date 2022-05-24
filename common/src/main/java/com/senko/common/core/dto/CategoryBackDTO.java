package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台文章DTO
 *
 * @author senko
 * @date 2022/5/23 8:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台文章DTO")
public class CategoryBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ApiModelProperty("分类id")
    private Integer id;

    /**
     * 分类名
     */
    @ApiModelProperty("分类名")
    private String categoryName;

    /**
     * 该分类下的文章数量
     */
    @ApiModelProperty("文章数量")
    private Integer articleCount;

    /**
     * 分类创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
