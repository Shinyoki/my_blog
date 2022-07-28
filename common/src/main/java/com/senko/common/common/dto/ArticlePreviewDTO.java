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
 * @author senko
 * @date 2022/7/28 11:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("文章预览dto")
public class ArticlePreviewDTO {

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private Long id;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

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
     * 文章分类
     */
    @ApiModelProperty("文章分类")
    private String categoryName;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Integer categoryId;

    /**
     * 发表时间
     */
    @ApiModelProperty("发表时间")
    private LocalDateTime createTime;

    /**
     * 文章标签
     */
    @ApiModelProperty("文章标签")
    private List<TagDTO> tagDTOList;

}
