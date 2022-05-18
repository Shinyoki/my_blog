package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改文章置顶VO
 *
 * @author senko
 * @date 2022/5/18 10:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("修改文章置顶VO")
public class ArticleTopVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空")
    @ApiModelProperty("文章ID")
    private Integer id;

    /**
     * 置顶 0:否 1:是
     */
    @NotNull(message = "置顶状态不能为空")
    @ApiModelProperty("是否置顶 0:否 1:是 ")
    private Integer isTop;
}
