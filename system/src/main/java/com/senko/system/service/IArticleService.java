package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.ArticleBackDTO;
import com.senko.common.core.entity.ArticleEntity;
import com.senko.common.core.vo.ArticleVO;
import com.senko.common.core.vo.ConditionVO;

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
}

