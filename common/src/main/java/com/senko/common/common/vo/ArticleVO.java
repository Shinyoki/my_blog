package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 前端传输的文章VO
 * @author senko
 * @date 2022/5/14 18:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("前端传输的文章")
public class ArticleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ApiModelProperty("文章id")
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    @NotBlank(message = "文章标题为空")
    private String articleTitle;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    /**
     * 文章封面
     */
    @ApiModelProperty("文章封面")
    private String articleCover;

    /**
     * 文章分类
     */
    @ApiModelProperty("文章分类")
    private String categoryName;

    /**
     * 文章标签
     */
    @ApiModelProperty("文章标签")
    private List<String> tagNameList;

    /**
     * 文章类型
     */
    @ApiModelProperty("文章类型")
    private Integer type;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @ApiModelProperty("文章状态")
    private Integer status;

    /**
     * 原文链接
     */
    @ApiModelProperty("原文链接")
    private String originalUrl;

    /**
     * 是否置顶
     */
    @ApiModelProperty("是否置顶")
    private Integer isTop;
}
