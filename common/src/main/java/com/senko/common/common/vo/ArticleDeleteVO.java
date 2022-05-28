package com.senko.common.common.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 文章逻辑删除相关DTO
 * @author senko
 * @date 2022/5/18 18:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("文章逻辑删除相关DTO")
public class ArticleDeleteVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 需操作id集合
     */
    @NotEmpty(message = "文章id集合不能为空")
    @ApiModelProperty(value = "需操作文章id集合", required = true,dataType = "List<Integer>")
    private List<Integer> idList;

    /**
     * 逻辑删除状态：0: false, 1: true
     */
    @NotNull(message = "逻辑删除状态码不能为空")
    @ApiModelProperty(value = "逻辑删除状态：0: false, 1: true",required = true,dataType = "Integer")
    private Integer isDelete;

}
