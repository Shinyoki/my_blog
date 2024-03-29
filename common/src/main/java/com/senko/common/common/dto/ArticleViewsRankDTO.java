package com.senko.common.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 文章排行
 *
 * 以浏览量作为排序
 *
 * @author senko
 * @date 2022/5/6 21:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ArticleViewsRankDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文章标题内容
     */
    private String articleTitle;

    /**
     * 浏览量
     */
    private Integer viewsCount;
}
