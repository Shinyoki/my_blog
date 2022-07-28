package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签云DTO
 *
 * @author senko
 * @date 2022/7/28 9:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("标签云dto")
public class TagCountDTO {

    /**
     * 标签id
     */
    @ApiModelProperty("标签id")
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String tagName;

    /**
     * 标签文章数量
     */
    @ApiModelProperty("标签文章数量")
    private Integer tagCount;

}
