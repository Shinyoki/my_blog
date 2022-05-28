package com.senko.common.common.dto;

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
 * 后台文章 传输层DTO
 *
 * 不带内容
 * @author senko
 * @date 2022/5/15 11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台文章DTO")
public class ArticleBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // ===============Entity================
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

    /**
     * 文章创建时间
     */
    @ApiModelProperty("文章创建时间")
    private LocalDateTime createTime;

    /**
     * 文章类型 1:原创 2:转载 3:翻译
     */
    @ApiModelProperty("文章类型 1:原创 2:转载 3:翻译")
    private Integer type;

    /**
     * 文章状态 1:公开 2:私密 3:评论可见
     */
    @ApiModelProperty("文章状态 1:公开 2:私密 3:评论可见")
    private Integer status;

    /**
     * 是否置顶 0:否 1:是
     */
    @ApiModelProperty("是否置顶")
    private Boolean isTop;

    /**
     * 是否删除 1:删除 0:未删除
     */
    @ApiModelProperty("是否删除 1:删除 0:未删除")
    private Boolean isDelete;

    // ===========needs================

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer likeCount;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer viewsCount;

    /**
     * 分类名
     */
    @ApiModelProperty("分类名")
    private String categoryName;

    /**
     * 标签 集合
     */
    @ApiModelProperty("标签 集合")
    private List<TagDTO> tagDTOList;
}
