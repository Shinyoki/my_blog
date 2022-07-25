package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 图库DTO
 *
 * @author senko
 * @date 2022/7/25 8:15
 */
@Data
@AllArgsConstructor
@NotNull
@Builder
@ApiModel(value = "图库DTO")
public class PhotoDTO {

    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面")
    private String photoAlbumCover;

    /**
     * 相册名
     */
    @ApiModelProperty(value = "相册名")
    private String photoAlbumName;

    /**
     * 图库
     */
    @ApiModelProperty(value = "图库")
    private List<String> photoList;

}
