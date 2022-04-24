package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ArticleTagMapper;
import com.senko.common.core.entity.ArticleTagEntity;
import com.senko.system.service.IArticleTagService;


@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTagEntity> implements IArticleTagService {

}