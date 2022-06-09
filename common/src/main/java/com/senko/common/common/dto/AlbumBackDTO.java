package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 后台相册DTO
 * @author senko
 * @date 2022/6/6 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("后台相册DTO")
public class AlbumBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 相册ID
     */
    @ApiModelProperty("相册ID")
    private Integer id;

    /**
     * 相册名称
     */
    @ApiModelProperty("相册名称")
    private String albumName;

    /**
     * 相册描述
     */
    @ApiModelProperty("相册描述")
    private String albumDesc;

    /**
     * 相册封面
     */
    @ApiModelProperty("相册封面")
    private String albumCover;

    /**
     * 相册状态 1:公开 2:私密
     */
    @ApiModelProperty("相册状态 1:公开 2:私密")
    private Integer status;

    /**
     * 相册内图片数量
     */
    @ApiModelProperty("相册内图片数量")
    private Integer photoCount;
}
