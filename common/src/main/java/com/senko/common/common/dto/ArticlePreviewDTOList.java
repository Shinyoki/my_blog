package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author senko
 * @date 2022/7/28 13:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("文章预览dto")
public class ArticlePreviewDTOList {

    /**
     * 文章列表
     */
    @ApiModelProperty("文章列表")
    private List<ArticlePreviewDTO> articlePreviewDTOList;

    /**
     * 条件名
     */
    @ApiModelProperty("条件名：分类、标签")
    private String name;
}
