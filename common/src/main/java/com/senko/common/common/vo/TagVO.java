package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 标签VO
 * @author senko
 * @date 2022/5/24 10:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("标签VO")
public class TagVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private Integer id;

    @NotBlank(message = "标签名不能为空")
    @ApiModelProperty(value = "标签名", required = true)
    private String tagName;
}
