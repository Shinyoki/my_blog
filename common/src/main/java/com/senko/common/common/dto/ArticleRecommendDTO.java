package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 推荐文章
 *
 * @author senko
 * @date 2022/7/17 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("推荐文章")
public class ArticleRecommendDTO {

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
     * 文章封面
     */
    @ApiModelProperty("文章封面")
    private String articleCover;

    /**
     * 发表时间
     */
    @ApiModelProperty("发表时间")
    private LocalDateTime createTime;

}
