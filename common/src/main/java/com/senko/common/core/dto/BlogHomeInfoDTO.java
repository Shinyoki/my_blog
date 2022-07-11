package com.senko.common.core.dto;

import com.senko.common.common.dto.PageDTO;
import com.senko.common.common.vo.WebsiteConfigVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 博客首页信息
 * @author senko
 * @date 2022/7/9 13:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "博客首页信息")
public class BlogHomeInfoDTO {
    /**
     * 所有文章数量
     */
    @ApiModelProperty("所有文章数量")
    private Integer articleCount;

    /**
     * 分类数量
     */
    @ApiModelProperty("分类数量")
    private Integer categoryCount;

    /**
     * 标签数量
     */
    @ApiModelProperty("标签数量")
    private Integer tagCount;

    /**
     * 访问量
     */
    @ApiModelProperty("访问量")
    private String viewsCount;

    /**
     * 网站配置
     */
    @ApiModelProperty("网站配置")
    private WebsiteConfigVO websiteConfig;

    /**
     * 页面列表
     */
    @ApiModelProperty("页面列表")
    private List<PageDTO> pageList;
}
