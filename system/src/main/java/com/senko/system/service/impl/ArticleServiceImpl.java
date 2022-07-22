package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.*;
import com.senko.common.common.entity.ArticleEntity;
import com.senko.common.common.entity.ArticleTagEntity;
import com.senko.common.common.entity.CategoryEntity;
import com.senko.common.common.entity.TagEntity;
import com.senko.common.common.vo.ArticleTopVO;
import com.senko.common.common.vo.ArticleVO;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.enums.ArticleStatusEnum;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.ArticleMapper;
import com.senko.system.mapper.ArticleTagMapper;
import com.senko.system.mapper.CategoryMapper;
import com.senko.system.mapper.TagMapper;
import com.senko.system.service.IArticleService;
import com.senko.system.service.IArticleTagService;
import com.senko.system.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.senko.common.constants.RedisConstants.ARTICLE_LIKE_COUNT_TAG;
import static com.senko.common.constants.RedisConstants.ARTICLE_VIEWS_COUNT_TAG;

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

    /**
     * HttpSession和HttpServletRequest都开以通过注入的方式获取
     */
    private HttpSession httpSession;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper, IArticleTagService articleTagService, ArticleTagMapper articleTagMapper, ITagService tagService, RedisHandler redisHandler, TagMapper tagMapper, HttpSession httpSession) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.articleTagService = articleTagService;
        this.articleTagMapper = articleTagMapper;
        this.tagService = tagService;
        this.redisHandler = redisHandler;
        this.tagMapper = tagMapper;
        this.httpSession = httpSession;
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
        // 快速查询文章总量，判断总数是0就退出
        Integer counts = articleMapper.countArticleBacks(conditionVO);
        if (counts == 0) {
            return new PageResult<>();
        }

        //总量非0，开始查询符合条件的字段，并封装成实体类
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

        return new PageResult<ArticleBackDTO>(counts, articleBackDTOList);
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
     * 修改文章置顶状态
     *
     * 针对修改操作记得加@Transactional注解
     * @param articleTopVO 文章置顶VO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticleTop(ArticleTopVO articleTopVO) {
        ArticleEntity article = ArticleEntity.builder()
                .id(articleTopVO.getId())
                .isTop(articleTopVO.getIsTop())
                .build();
        articleMapper.updateById(article);
    }

    /**
     * 更新文章id集合的逻辑删除码
     * @param deleteVO 查询VO : idList、isDelete
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticleDelete(DeleteVO deleteVO) {
        List<ArticleEntity> articles = deleteVO.getIdList().stream()
                .map(id -> {
                    return ArticleEntity.builder()
                            .id(id)
                            .isDelete(deleteVO.getIsDelete())
                            .build();
                })
                .collect(Collectors.toList());
        this.updateBatchById(articles);
    }

    /**
     * 完全删除文章id集合对应的数据
     * @param articleIdList 文章id 集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticles(List<Integer> articleIdList) {
        //删除与 文章 标签双向绑定的 tb_article_tag 表字段
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTagEntity>()
                .in(ArticleTagEntity::getArticleId, articleIdList));
        //删除文章
        articleMapper.deleteBatchIds(articleIdList);

    }

    /**
     * 查询首页的文章
     * @param conditionVO   查询条件
     * @return              首页文章集合
     */
    @Override
    public List<ArticleHomeDTO> listHomeArticles(ConditionVO conditionVO) {
        List<ArticleHomeDTO> articleHomeDTOList = articleMapper.listHomeArticles(PageUtils.getCurrent(), PageUtils.getSize());
        return articleHomeDTOList;
    }

    /**
     * 查询文章DTO
     * @param articleId 文章ID
     * @return          文章DTO
     */
    @Override
    public ArticleDTO getArticleDtoById(Integer articleId) {
        // 查询推荐列表：相同tag的文章
        CompletableFuture<List<ArticleRecommendDTO>> recommendArticleFuture = CompletableFuture.supplyAsync(() -> {
            return articleMapper.listRecommendArticles(articleId);
        });

        // 查询最新的6个文章
        CompletableFuture<List<ArticleRecommendDTO>> newestArticleListFuture = CompletableFuture.supplyAsync(() -> {
            List<ArticleEntity> articleEntities = articleMapper.selectList(new LambdaQueryWrapper<ArticleEntity>()
                    .select(ArticleEntity::getId, ArticleEntity::getArticleCover, ArticleEntity::getArticleTitle, ArticleEntity::getCreateTime)
                    .eq(ArticleEntity::getIsDelete, CommonConstants.FALSE)
                    .eq(ArticleEntity::getStatus, 1)
                    .orderByDesc(ArticleEntity::getId)
                    .last("limit 6"));
            return BeanCopyUtils.copyList(articleEntities, ArticleRecommendDTO.class);
        });

        // 查询文章详情
        ArticleDTO articleDTO = articleMapper.selectArticleDTOById(articleId);
        if (Objects.isNull(articleDTO)) {
            throw new ServiceException("该文章不存在");
        }

        // 更新文章的访问量
        updateArticleViewsCount(articleId);

        // 查询上一篇
        ArticleEntity lessThanArticle = articleMapper.selectOne(new LambdaQueryWrapper<ArticleEntity>()
                .select(ArticleEntity::getId, ArticleEntity::getArticleTitle, ArticleEntity::getArticleCover)
                .eq(ArticleEntity::getIsDelete, CommonConstants.FALSE)
                .eq(ArticleEntity::getStatus, 1)
                // 筛选出id更小的那个
                .lt(ArticleEntity::getId, articleId)
                .orderByDesc(ArticleEntity::getId)
                .last("limit 1"));
        articleDTO.setLastArticle(BeanCopyUtils.copyObject(lessThanArticle, ArticlePaginationDTO.class));

        // 查询下一篇
        ArticleEntity greaterThanArticle = articleMapper.selectOne(new LambdaQueryWrapper<ArticleEntity>()
                .select(ArticleEntity::getId, ArticleEntity::getArticleTitle, ArticleEntity::getArticleCover)
                .eq(ArticleEntity::getIsDelete, CommonConstants.FALSE)
                .eq(ArticleEntity::getStatus, 1)
                // 筛选出id更大的那个
                .gt(ArticleEntity::getId, articleId)
                .orderByAsc(ArticleEntity::getId)
                .last("limit 1"));
        articleDTO.setNextArticle(BeanCopyUtils.copyObject(greaterThanArticle, ArticlePaginationDTO.class));

        // 获取点赞量
        int likesCount = Optional.ofNullable((Double) redisHandler.hGet(ARTICLE_LIKE_COUNT_TAG, articleId.toString())).orElse(0D).intValue();
        articleDTO.setLikeCount(likesCount);

        // 获取阅读量
        int viewsCount = Optional.ofNullable(redisHandler.zScore(ARTICLE_VIEWS_COUNT_TAG, articleId)).orElse(0D).intValue();
        articleDTO.setViewsCount(viewsCount);

        /**
         * CompletableFuture，先supply，让线程池异步运行，因为这里发生的查询可能会很耗时
         * 于是我们先做其他的事，
         * 做的差不多了，再get，阻塞等待结果，最终消费结果。
         */
        try {
            articleDTO.setRecommendArticleList(recommendArticleFuture.get());
            articleDTO.setNewestArticleList(newestArticleListFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ServiceException("查询文章详情异常");
        }
        return articleDTO;
    }

    /**
     * 点赞文章
     */
    @Override
    public void doArticleLike(Integer articleId) {
        Integer userId = SecurityUtils.getLoginUser().getUserInfoId();
        String redisKey = RedisConstants.ARTICLE_USER_LIKE + userId;

        // 寻找是否存在于set中
        if (redisHandler.sIsMember(redisKey, articleId)) {
            // 已经点赞过，则取消点赞
            redisHandler.sRemove(redisKey, articleId);  // 删除set中的元素
            redisHandler.hDecrement(ARTICLE_LIKE_COUNT_TAG, articleId.toString(), 1); // 减少点赞量
        } else {
            redisHandler.sAdd(redisKey, articleId);
            redisHandler.hIncrement(ARTICLE_LIKE_COUNT_TAG, articleId.toString(), 1); // 增加点赞量
        }
    }

    /**
     * 在查询文章的时候，更新文章的访问量
     *
     * HttpSession 服务端的技术
     * 服务器会为每一个用户 创建一个独立的HttpSession
     *
     * HttpSession原理
     * 当用户第一次访问Servlet时,服务器端会给用户创建一个独立的Session
     * 并且生成一个SessionID,这个SessionID在响应浏览器的时候会被装进cookie中,从而被保存到浏览器中
     * 当用户再一次访问Servlet时,请求中会携带着cookie中的SessionID去访问
     * 服务器会根据这个SessionID去查看是否有对应的Session对象
     * 有就拿出来使用;没有就创建一个Session(相当于用户第一次访问)
     *
     * 域的范围:
     *     Context域 > Session域 > Request域
     *     Session域 只要会话不结束就会存在 但是Session有默认的存活时间(30分钟)
     * @param articleId 文章ID
     */
    private void updateArticleViewsCount(Integer articleId) {
        Object articleSetInCookie = Optional.ofNullable(httpSession.getAttribute(CommonConstants.ARTICLE_SET))
                .orElseGet(HashSet::new);
        Set<Integer> articleIds = ServletUtils.castSet(articleSetInCookie, Integer.class);
        if (!articleIds.contains(articleId)) {
            // 如果访问的文章不在cookie中，则 更新文章的访问量
            articleIds.add(articleId);
            // 更新session cookie
            httpSession.setAttribute(CommonConstants.ARTICLE_SET, articleIds);
            // 更新缓存
            redisHandler.zIncrement(ARTICLE_VIEWS_COUNT_TAG, articleId, 1D);
        }

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
