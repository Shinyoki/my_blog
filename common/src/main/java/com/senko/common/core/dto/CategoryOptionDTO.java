package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类选项
 *
 * @author senko
 * @date 2022/5/14 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryOptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;
}
