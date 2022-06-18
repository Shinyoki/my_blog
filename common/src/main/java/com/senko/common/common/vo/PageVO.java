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
 * 页面VO
 * @author senko
 * @date 2022/6/18 7:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "页面")
public class PageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页面ID
     */
    @ApiModelProperty("页面ID")
    private Integer id;

    /**
     * 页面名
     */
    @ApiModelProperty(value = "页面名",required = true)
    @NotBlank(message = "页面名称不能为空")
    private String pageName;

    /**
     * 页面标签
     */
    @ApiModelProperty(value = "页面标签",required = true)
    @NotBlank(message = "页面标签不能为空")
    private String pageLabel;

    /**
     * 页面封面
     */
    @ApiModelProperty(value = "页面封面",required = true)
    @NotBlank(message = "页面封面不能为空")
    private String pageCover;
}
