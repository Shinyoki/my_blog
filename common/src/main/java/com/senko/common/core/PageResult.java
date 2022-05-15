package com.senko.common.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象DTO
 *
 * @author senko
 * @date 2022/5/15 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("分页对象")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    @ApiModelProperty("总数")
    private Integer count;

    /**
     * 分页对象 集合
     */
    @ApiModelProperty("分页对象集合")
    private List<T> recordList;
}
