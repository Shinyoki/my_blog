package com.senko.common.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 博客信息
 *
 * 记录着来自多个表的访问数量
 *
 * @author senko
 * @date 2022/5/6 19:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogCountsInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 访问量
     */
    private Integer viewsCount;

    /**
     * 消息/留言量
     */
    private Integer messagesCount;

    /**
     * 用户量
     */
    private Integer usersCount;

    /**
     * 文章总量
     */
    private Integer articlesCount;

    //=====================List=================

    /**
     * 文章分类 集合
     */
    private List<CategoryDTO> categoryList;

    /**
     * 文章标签 集合
     */
    private List<TagDTO> tagList;

    /**
     * 某日文章数量 集合
     */
    private List<ArticlesOnOneDayDTO> articlesOnOneDayList;


    /**
     * 某日阅览数量 集合
     */
    private List<UniqueViewDTO> uniqueViewDTOList;

    /**
     * 文章浏览量 排行：倒序
     */
    private List<ArticleViewsRankDTO> articleViewsRankList;
}
