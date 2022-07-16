package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 首页里的文章DTO
 *
 * @author senko
 * @date 2022/7/16 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("首页里的文章DTO")
public class ArticleHomeDTO {

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private Integer id;

    /**
     * 文章缩略图
     */
    @ApiModelProperty("文章缩略图")
    private String articleCover;

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    private String articleTitle;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String articleContent;

    /**
     * 是否置顶
     */
    @ApiModelProperty("是否置顶")
    private Integer isTop;

    /**
     * 文章类型
     */
    @ApiModelProperty("文章类型")
    private Integer type;

    /**
     * 文章分类id
     */
    @ApiModelProperty("文章分类id")
    private Integer categoryId;

    /**
     * 文章分类名称
     */
    @ApiModelProperty("文章分类名称")
    private String categoryName;

    /**
     * 文章标签
     */
    @ApiModelProperty("文章标签")
    private List<TagDTO> tagDTOList;

    /**
     * 发表时间
     */
    @ApiModelProperty("发表时间")
    private LocalDateTime createTime;

}
