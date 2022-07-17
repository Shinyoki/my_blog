package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章上下篇
 *
 * @author senko
 * @date 2022/7/17 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("文章上下篇")
public class ArticlePaginationDTO {

    /**
     * 文章id
     */
    @ApiModelProperty("文章id")
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    private String articleTitle;

    /**
     * 文章缩略图
     */
    @ApiModelProperty("文章缩略图")
    private String articleCover;

}
