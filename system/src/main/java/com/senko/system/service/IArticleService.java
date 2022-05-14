package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.entity.ArticleEntity;
import com.senko.common.core.vo.ArticleVO;

import java.util.Map;

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
}

