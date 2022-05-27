package com.senko.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 符合ElementUI Tree的标签选项的数据结构DTO
 *
 * @author senko
 * @date 2022/5/26 10:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ElementTree LabelOption DTO")
public class ElementTreeLabelOptionDTO implements Serializable {
    private static final long serialVersionUID = -8984564352799160582L;
    /**
     * PO层对象ID
     */
    @ApiModelProperty("PO层对象ID")
    private Integer id;

    /**
     * 选项名称
     */
    @ApiModelProperty("选项名称")
    private String label;

    /**
     * 子选项
     */
    @ApiModelProperty("子选项")
    private List<ElementTreeLabelOptionDTO> children;
}
