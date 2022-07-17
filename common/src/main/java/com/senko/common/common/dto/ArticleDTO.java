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
 * 文章
 *
 * @author senko
 * @date 2022/7/17 14:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("文章")
public class ArticleDTO {

    /**
     * 文章ID
     */
    @ApiModelProperty("文章DTO")
    private Integer id;

    /**
     * 文章封面
     */
    @ApiModelProperty("文章封面")
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
     * 点赞量
     */
    @ApiModelProperty("点赞量")
    private Integer likeCount;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer viewsCount;

    /**
     * 文章类型 1:原创 2:转载 3:翻译
     */
    @ApiModelProperty("文章类型 1:原创 2:转载 3:翻译")
    private Integer type;

    /**
     * 原文地址
     */
    @ApiModelProperty("原文地址")
    private String originalUrl;

    /**
     * 分类ID
     */
    @ApiModelProperty("分类ID")
    private Integer categoryId;

    /**
     * 分类名
     */
    @ApiModelProperty("分类名")
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

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 上一篇文章
     */
    @ApiModelProperty("上一篇文章")
    private ArticlePaginationDTO lastArticle;

    /**
     * 下一篇文章
     */
    @ApiModelProperty("下一篇文章")
    private ArticlePaginationDTO nextArticle;

    /**
     * 推荐文章集合
     */
    @ApiModelProperty("推荐文章集合")
    private List<ArticleRecommendDTO> recommendArticleList;

    /**
     * 最新文章集合
     */
    @ApiModelProperty("最新文章集合")
    private List<ArticleRecommendDTO> newestArticleList;

}
