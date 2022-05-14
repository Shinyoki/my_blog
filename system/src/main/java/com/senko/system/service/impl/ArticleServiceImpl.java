package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.core.entity.ArticleTagEntity;
import com.senko.common.core.entity.CategoryEntity;
import com.senko.common.core.entity.TagEntity;
import com.senko.common.core.vo.ArticleVO;
import com.senko.common.enums.ArticleStatusEnum;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.ArticleTagMapper;
import com.senko.system.mapper.CategoryMapper;
import com.senko.system.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ArticleMapper;
import com.senko.common.core.entity.ArticleEntity;
import com.senko.system.service.IArticleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章服务
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

    private ArticleMapper articleMapper;

    private CategoryMapper categoryMapper;

    private ArticleTagMapper articleTagMapper;

    private ITagService tagService;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper, ArticleTagMapper articleTagMapper, ITagService tagService) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.articleTagMapper = articleTagMapper;
        this.tagService = tagService;
    }

    /**
     * 保存或更新文章
     * @param articleVO 前端传来的文章(需传入正确的id)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateArticle(ArticleVO articleVO) {
        //保存文章分类
        CategoryEntity category = saveArticleCategory(articleVO);

        //转换为ArticleEntity
        ArticleEntity article = BeanCopyUtils.copyObject(articleVO, ArticleEntity.class);
        //categoryId
        if (StringUtils.isNotNull(category)) {
            article.setCategoryId(category.getId());
        }
        article.setUserId(SecurityUtils.getLoginUser().getUserInfoId());

        //更新 / 保存文章
        this.saveOrUpdate(article);

        //保存 文章相关Tag标签
        saveArticleTag(articleVO);
    }

    /**
     * 保存文章标签
     * @param articleVO
     */
    private void saveArticleTag(ArticleVO articleVO) {
        if (StringUtils.isNotNull(articleVO.getId())) {
            //先删除所有 和文章绑定的标签
            articleTagMapper.delete(new LambdaQueryWrapper<ArticleTagEntity>()
                    .eq(ArticleTagEntity::getArticleId, articleVO.getId()));

            //添加文章标签
            List<String> tagNameList = articleVO.getTagNameList();
            if (CollectionUtils.isNotEmpty(tagNameList)) {
                //查询剩余还能匹配上的标签：
                List<TagEntity> tagsInNameList = tagService.list(new LambdaQueryWrapper<TagEntity>()
                        .in(TagEntity::getTagName, tagNameList));

                //能找到剩余标签的ids names
                List<String> tagNames = tagsInNameList.stream()
                        .map(TagEntity::getTagName)
                        .collect(Collectors.toList());
                List<Integer> tagIds = tagsInNameList.stream()
                        .map(TagEntity::getId)
                        .collect(Collectors.toList());

                //减去剩余的，就是新标签
                tagNameList.removeAll(tagNames);
                if (CollectionUtils.isNotEmpty(tagNameList)) {
                    //有新标签就转换并保存
                    List<TagEntity> newTags = tagNameList.stream()
                            .map(name -> {
                                return TagEntity.builder()
                                        .tagName(name)
                                        .build();
                            })
                            .collect(Collectors.toList());
                    tagService.saveBatch(newTags);
                    //TODO MP save or update
                }
            }
        } else {
            return;
        }
    }

    /**
     * 保存文章VO的分类，如果已存在同名分类则不保存
     * @param articleVO 前端传来的ArticleVO
     * @return          CategoryEntity
     */
    private CategoryEntity saveArticleCategory(ArticleVO articleVO) {
        LambdaQueryWrapper<CategoryEntity> query = new LambdaQueryWrapper<CategoryEntity>()
                .eq(CategoryEntity::getCategoryName, articleVO.getCategoryName());
        //coud be null
        CategoryEntity category = categoryMapper.selectOne(query);

        //该分类不存在 && 传过来的文件不是草稿
        if (StringUtils.isNull(category) && StringUtils.isNotNull(articleVO.getStatus()) &&
                !articleVO.getStatus().equals(ArticleStatusEnum.DRAFT.getStatus())) {
            //存储该分类
            category = CategoryEntity.builder()
                    .categoryName(articleVO.getCategoryName())
                    .build();
            categoryMapper.insert(category);
        }
        return category;
    }
}
