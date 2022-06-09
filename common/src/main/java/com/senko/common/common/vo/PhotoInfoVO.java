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
 * 图片信息VO
 * @author senko
 * @date 2022/6/8 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "图片信息")
public class PhotoInfoVO implements Serializable {
    private static final long serialVersionUID = -8984100898410089841L;

    /**
     * 图片id
     */
    @NotNull(message = "图片id不能为空")
    @ApiModelProperty(name = "id", value = "图片id", required = true, dataType = "Integer")
    private Integer id;

    /**
     * 图片名
     */
    @NotBlank(message = "图片名不能为空")
    @ApiModelProperty(name = "photoName", value = "图片名", required = true, dataType = "String")
    private String photoName;

    /**
     * 图片描述
     */
    @ApiModelProperty(name = "photoDesc", value = "图片描述", dataType = "String")
    private String photoDesc;
}
