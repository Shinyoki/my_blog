package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 标签DTO
 * @author senko
 * @date 2022/5/24 8:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("标签DTO")
public class TagBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @ApiModelProperty("标签ID")
    private Integer id;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String tagName;

    /**
     * 文章数量
     */
    @ApiModelProperty("使用该标签的文章总数")
    private Integer articleCount;

    /**
     * 创建时间
     */
    @ApiModelProperty("标签的创建时间")
    private LocalDateTime createTime;
}
