package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 说说VO
 * @author senko
 * @date 2022/5/30 21:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("说说VO")
public class TalkVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 说说ID
     */
    @ApiModelProperty("说说ID")
    private Integer id;

    /**
     * 说说内容
     */
    @ApiModelProperty(value = "说说内容",required = true)
    @NotEmpty(message = "说说内容不能为空")
    private String content;

    /**
     * 状态 1-公开 2-私密
     */
    @ApiModelProperty(value = "状态 1-公开 2-私密",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 图片集合
     */
    @ApiModelProperty(value = "图片集合")
    private String images;

    /**
     * 置顶 1-置顶 0-不置顶
     */
    @ApiModelProperty(value = "置顶 1-置顶 0-不置顶",required = true)
    @NotNull(message = "置顶不能为空")
    private Integer isTop;
}
