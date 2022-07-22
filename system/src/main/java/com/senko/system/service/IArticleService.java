package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.ArticleDTO;
import com.senko.common.common.dto.ArticleHomeDTO;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.ArticleBackDTO;
import com.senko.common.common.entity.ArticleEntity;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.common.vo.ArticleTopVO;
import com.senko.common.common.vo.ArticleVO;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;

/**
 * 文章服务
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
public interface IArticleService extends IService<ArticleEntity> {

    /**
     * 保存或更新文章
     */
    void saveOrUpdateArticle(ArticleVO articleVO);

    /**
     * 查询 后台文章（不带Content，有点赞数等特殊信息）
     * @param conditionVO 查询条件
     * @return 后台文章集合
     */
    PageResult<ArticleBackDTO> listArticleBacks(ConditionVO conditionVO);


    /**
     * 根据文章ID获取 文章
     * @param articleId     文章id
     * @return              前端需要的文章
     */
    ArticleVO getArticleBackByArticleId(Integer articleId);

    /**
     * 修改文章置顶状态
     * @param articleTopVO 文章置顶VO
     */
    void updateArticleTop(ArticleTopVO articleTopVO);

    /**
     * 更新文章id集合的逻辑删除码
     * @param deleteVO 查询VO : idList、isDelete
     */
    void updateArticleDelete(DeleteVO deleteVO);


    /**
     * 完全删除文章id集合对应的数据
     * @param articleIdList 文章id 集合
     */
    void deleteArticles(List<Integer> articleIdList);

    /**
     * 查询首页的文章
     * @param conditionVO   查询条件
     * @return              首页文章集合
     */
    List<ArticleHomeDTO> listHomeArticles(ConditionVO conditionVO);

    /**
     * 查询文章DTO
     * @param articleId 文章ID
     * @return          文章DTO
     */
    ArticleDTO getArticleDtoById(Integer articleId);

    /**
     * 点赞文章
     */
    void doArticleLike(Integer articleId);
}

