package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 相册VO
 * @author senko
 * @date 2022/6/7 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("相册VO")
public class PhotoAlbumVO implements Serializable {
    private static final long serialVersionUID = -812098984L;

    /**
     * 相册ID
     */
    @ApiModelProperty("相册ID")
    private Integer id;

    /**
     * 相册名称
     */
    @ApiModelProperty(value = "相册名称",required = true)
    @NotBlank(message = "相册名称不能为空")
    private String albumName;

    /**
     * 相册描述
     */
    @ApiModelProperty(value = "相册描述",required = true)
    @NotBlank(message = "相册描述不能为空")
    private String albumDesc;

    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面",required = true)
    @NotBlank(message = "相册封面不能为空")
    private String albumCover;

    /**
     * 相册状态 1:公开 2:私密
     */
    @ApiModelProperty(value = "相册状态 1:公开 2:私密", required = true)
    @NotNull(message = "相册状态不能为空")
    private Integer status;
}
