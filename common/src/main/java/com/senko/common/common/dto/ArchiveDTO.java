package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 归档DTO
 *
 * @author senko
 * @date 2022/7/24 15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("归档DTO")
public class ArchiveDTO {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String articleTitle;

    /**
     * 发表时间
     */
    @ApiModelProperty("发表时间")
    private LocalDateTime createTime;

}
