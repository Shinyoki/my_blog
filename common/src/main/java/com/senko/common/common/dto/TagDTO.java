package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 标签 DTO
 *
 * @author senko
 * @date 2022/5/6 19:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel("标签DTO")
public class TagDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private Integer id;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String tagName;

}
