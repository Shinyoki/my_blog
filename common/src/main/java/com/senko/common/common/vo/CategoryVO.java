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
 * 分类VO
 * @author senko
 * @date 2022/5/23 12:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("分类VO")
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @ApiModelProperty("分类ID")
    private Integer id;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名",required = true)
    @NotBlank(message = "分类名不能为空")
    private String categoryName;
}
