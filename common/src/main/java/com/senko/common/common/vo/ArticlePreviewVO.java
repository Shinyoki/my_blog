package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预览文章VO
 *
 * @author senko
 * @date 2022/7/28 13:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "预览文章VO")
public class ArticlePreviewVO {

    /**
     * 当前起点数
     */
    @ApiModelProperty(name = "current", value = "当前起点数", dataType = "Long")
    private Long current;

    /**
     * 每页条数
     */
    @ApiModelProperty(name = "size", value = "每页条数", dataType = "Long")
    private Long size;

    /**
     * 标签id
     */
    @ApiModelProperty(name = "tagId", value = "标签id", dataType = "Integer")
    private Integer tagId;

    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", dataType = "Integer")
    private Integer categoryId;

}
