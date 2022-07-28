package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TOPIC id 与 评论数映射DTO
 *
 * @author senko
 * @date 2022/7/25 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("TOPIC id 与 评论数映射DTO")
public class CommentCountDTO {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 评论数量
     */
    @ApiModelProperty("评论数量")
    private Integer commentCount;

}
