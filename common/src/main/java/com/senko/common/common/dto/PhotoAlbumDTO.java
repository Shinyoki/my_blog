package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 相册
 *
 * @author senko
 * @date 2022/7/25 7:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "相册")
public class PhotoAlbumDTO {

    /**
     * 相册ID
     */
    @ApiModelProperty(value = "相册ID")
    private Integer id;

    /**
     * 相册名
     */
    @ApiModelProperty(value = "相册名")
    private String albumName;

    /**
     * 相册描述
     */
    @ApiModelProperty(value = "相册描述")
    private String albumDesc;

    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面")
    private String albumCover;

}
