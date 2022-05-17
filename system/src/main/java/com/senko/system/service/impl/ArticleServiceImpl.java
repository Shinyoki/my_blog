package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.ArticleBackDTO;
import com.senko.common.core.entity.ArticleTagEntity;
import com.senko.common.core.entity.CategoryEntity;
import com.senko.common.core.entity.TagEntity;
import com.senko.common.core.vo.ArticleVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.enums.ArticleStatusEnum;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.ArticleTagMapper;
import com.senko.system.mapper.CategoryMapper;
import com.senko.system.mapper.TagMapper;
import com.senko.system.service.IArticleTagService;
import com.senko.system.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ArticleMapper;
import com.senko.common.core.entity.ArticleEntity;
import com.senko.system.service.IArticleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.senko.common.constants.RedisConstants.*;

/**
 * 文章服务
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

    private ArticleMapper articleMapper;

    private CategoryMapper categoryMapper;

    private IArticleTagService articleTagService;

    private ArticleTagMapper articleTagMapper;

    private ITagService tagService;

    private RedisHandler redisHandler;

    private TagMapper tagMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper, IArticleTagService articleTagService, ArticleTagMapper articleTagMapper, ITagService tagService, RedisHandler redisHandler, TagMapper tagMapper) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.articleTagService = articleTagService;
        this.articleTagMapper = articleTagMapper;
        this.tagService = tagService;
        this.redisHandler = redisHandler;
        this.tagMapper = tagMapper;
    }

    /**
     * 保存或更新文章
     * @param articleVO 前端传来的文章(需传入正确的id)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateArticle(ArticleVO articleVO) {
        //保存 文章分类
        CategoryEntity category = saveArticleCategory(articleVO);

        //转换为ArticleEntity
        ArticleEntity article = BeanCopyUtils.copyObject(articleVO, ArticleEntity.class);
        //categoryId
        if (StringUtils.isNotNull(category)) {
            article.setCategoryId(category.getId());
        }
        article.setUserId(SecurityUtils.getLoginUser().getUserInfoId());

        /**
         *  更新 / 保存文章
         * MP的saveOrUpdate根据@TableId对应字段有无值来决定是更新还是保存，反正这里基本就只有保存了
         *
         * MP的很多操作在执行后会顺带修改 传入的实参对象，因此一开始这里传到article没有id值，
         * 经过这一步骤后article就拥有了id
         */
        //保存 文章
        this.saveOrUpdate(article);

        //保存 文章相关Tag标签
        saveArticleTag(articleVO, article.getId());
    }

    /**
     * 查询 后台文章
     * @param conditionVO 查询条件
     * @return 后台文章集合
     */
    @Override
    public PageResult<ArticleBackDTO> listArticleBacks(ConditionVO conditionVO) {
        // 查询文章总量
        Integer counts = articleMapper.countArticleBacks(conditionVO);
        if (counts == 0) {
            return new PageResult<>();
        }

        List<ArticleBackDTO> articleBackDTOList = articleMapper.listArticleBacks(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);

        //缓存查询
        //浏览量 map{articleId, scores}
        Map<Object, Double> viewsMap = redisHandler.zAllRangeWithScore(ARTICLE_VIEWS_COUNT_TAG);
        //点赞量 map{articleId, likes}
        Map<String, Object> likesMap = redisHandler.hGetAll(ARTICLE_LIKE_COUNT_TAG);

        //转换 浏览量&点赞量
        articleBackDTOList.forEach(articleBackDTO -> {
            //浏览量
            Double views = viewsMap.get(articleBackDTO.getId());
            if (Objects.nonNull(views)) {
                articleBackDTO.setViewsCount(views.intValue());
            }
            //点赞量
            Object likes = likesMap.get(articleBackDTO.getId().toString());
            if (Objects.nonNull(likes)) {
                articleBackDTO.setLikeCount((Integer) likes);
            }
        });

        return new PageResult<ArticleBackDTO>(articleBackDTOList.size(), articleBackDTOList);
    }

    /**
     * 根据文章ID获取 文章
     * @param articleId     文章id
     * @return              前端需要的文章
     */
    @Override
    public ArticleVO getArticleBackByArticleId(Integer articleId) {
        //查询文章
        ArticleEntity articleEntity = articleMapper.selectById(articleId);
        if (Objects.isNull(articleEntity)) {
            return null;
        }
        //分类
        CategoryEntity categoryEntity = categoryMapper.selectById(articleEntity.getCategoryId());
        //标签
        List<String> tagNameList = tagMapper.listTagNameByArticleId(articleId);

        //封装信息
        ArticleVO articleVO = BeanCopyUtils.copyObject(articleEntity, ArticleVO.class);
        if (Objects.nonNull(categoryEntity)) {
            articleVO.setCategoryName(categoryEntity.getCategoryName());
        }
        articleVO.setTagNameList(tagNameList);

        return articleVO;
    }

    /**
     * 保存 文章:标签
     * @param articleVO 前端传来的参数，如果带有id就删除对应文章和标签的绑定，再执行
     * @param articleId 需要绑定标签的文章id
     */
    private void saveArticleTag(ArticleVO articleVO, Integer articleId) {
        if (StringUtils.isNotNull(articleVO.getId())) {
            //先删除所有 和文章Vo绑定的标签
            articleTagMapper.delete(new LambdaQueryWrapper<ArticleTagEntity>()
                    .eq(ArticleTagEntity::getArticleId, articleVO.getId()));
        }
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
                //保存 新标签
                tagService.saveBatch(newTags);

                //添加 新ids
                List<Integer> newTagIds = newTags.stream()
                        .map(TagEntity::getId)
                        .collect(Collectors.toList());
                tagIds.addAll(newTagIds);
            }

            //绑定 文章:标签
            List<ArticleTagEntity> resultArticleTagList = tagIds.stream()
                    .map(id -> {
                        return ArticleTagEntity.builder()
                                .articleId(articleId)
                                .tagId(id)
                                .build();
                    })
                    .collect(Collectors.toList());

            articleTagService.saveBatch(resultArticleTagList);
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
        //could be null
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
