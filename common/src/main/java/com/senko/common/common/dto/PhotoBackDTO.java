package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 后台图片DTO
 * @author senko
 * @date 2022/6/7 21:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台图片DTO")
public class PhotoBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 图片ID
     */
    @ApiModelProperty("图片ID")
    private Integer id;

    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String photoName;

    /**
     * 图片描述
     */
    @ApiModelProperty("图片描述")
    private String photoDesc;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String photoSrc;
}
