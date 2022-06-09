package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 上传图片VO
 * @author senko
 * @date 2022/6/8 11:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "上传图片VO")
public class PhotoVO implements Serializable {
    private static final long serialVersionUID = -8984100898410089841L;

    /**
     * 相册id
     */
    @NotNull
    @ApiModelProperty(name = "albumId", value = "相册id", required = true, dataType = "Integer")
    private Integer albumId;

    /**
     * 照片url列表
     */
    @ApiModelProperty(name = "photoUrlList", value = "照片列表", required = false, dataType = "List<String>")
    private List<String> photoUrlList;

    /**
     * 照片id列表
     */
    @ApiModelProperty(name = "photoIdList", value = "照片id列表", required = false, dataType = "List<Integer>")
    private List<Integer> photoIdList;
}
