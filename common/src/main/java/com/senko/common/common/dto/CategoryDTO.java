package com.senko.common.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 分类 DTO
 *
 * @author senko
 * @date 2022/5/6 19:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID  = 1L;

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 该分类下所具有的文章数量
     */
    private Integer articlesCount;

}
