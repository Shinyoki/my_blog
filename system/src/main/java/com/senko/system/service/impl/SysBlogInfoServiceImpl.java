package com.senko.system.service.impl;

import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.common.dto.*;
import com.senko.common.common.entity.ArticleEntity;
import com.senko.common.common.vo.WebsiteConfigVO;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.dto.BlogHomeInfoDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.enums.UserTypeEnum;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.*;
import com.senko.system.service.IPageService;
import com.senko.system.service.ISysBlogInfoService;
import com.senko.system.service.IUniqueViewService;
import com.senko.system.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.senko.common.constants.RedisConstants.*;
/**
 * 博客相关
 * @author senko
 * @date 2022/5/9 13:01
 */
@Service
public class SysBlogInfoServiceImpl implements ISysBlogInfoService {
    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IUniqueViewService uniqueViewService;

    @Autowired
    private UniqueViewMapper uniqueViewMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    @Autowired
    private IPageService pageService;



    /**
     * 记录当前访问
     */
    @Override
    public void recordCurView() {
        //获取IP地址
        String ipAddress = IpUtils.getIpAddressFromRequest(ServletUtils.getRequest());
        // user agent
        UserAgent userAgent = ServletUtils.getUserAgent();
        //browser & system
        Browser browser = userAgent.getBrowser();
        OS os = userAgent.getOs();
        //简单的用户标志 ip+浏览器+系统
        String uuid = ipAddress + browser.getName() + os.getName();
        /**
         *  有些敏感的数据，不能直接明文保存到数据库中，需要加密。MD5是目前比较常见的一种加密方式。
         * 网上好多都是自己写一个加密工具类，其实springboot库里面是有现成的轮子。轮子有现成的，原则上就用现成的~~
         *
         * import org.springframework.util.DigestUtils;
         *
         * 比如对密码进行 md5 加密
         * String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
         */
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes(StandardCharsets.UTF_8));

        //1.地域信息
        if (redisHandler.sIsNotMember(RedisConstants.UNIQUE_VISITOR, md5)) {
            //不在集合中，就计算

            //地理
            String ipSource = IpUtils.getIpSource(ipAddress);
            if (StringUtils.isNotBlank(ipSource)) {
                //向 地域map中插入相关数据
                if (ipAddress.equals("未知")) {
                    redisHandler.hIncrement(RedisConstants.VISITOR_AREA, RedisConstants.UNKNOWN, 1L);
                } else {
                    redisHandler.hIncrement(RedisConstants.VISITOR_AREA, ipSource, 1L);
                }
            }

            //2.访问数量
            redisHandler.increment(RedisConstants.BLOG_VIEWS_COUNT_TAG, 1L);

            //3.来访者 记录
            redisHandler.sAdd(RedisConstants.UNIQUE_VISITOR, md5);
        }

    }

    /**
     * 获取各种已有的访问量、以及分类等集合的统计
     */
    @Override
    public BlogCountsInfoDTO getBasicAdminInfo() {
        //=============Counts==========
        //访问量：redis获取到的值有NULL的情况，不会自动设置为0
        Integer views = (Integer) redisHandler.get(BLOG_VIEWS_COUNT_TAG);
        Integer viewsCount = Optional.ofNullable(views).orElse(0);
        //留言数量：表内没有字段，就返回0，不会返回NULL
        Long messageCount = messageMapper.selectCount(null);
        //用户数量
        Long usersCount = userInfoMapper.selectCount(null);
        //文章总量 逻辑删除，貌似MP的提供的查询方法会自动根据配置文件去拼接逻辑删除判断，有的则不能
        Long articleCount = articleMapper.selectCount(new LambdaQueryWrapper<ArticleEntity>()
                .eq(ArticleEntity::getIsDelete, 0));
        //==============List===========
        //分类集合 ：内部记录分类下文章的数量
        List<CategoryDTO> categoryDTOS = categoryMapper.listOfCategoryDTO();
        //标签集合（不是单纯的获取数量，因此用的是select list 而非select count
        List<TagDTO> tagDTOS = BeanCopyUtils.copyList(tagMapper.selectList(null), TagDTO.class);
        //文章集合
        List<ArticlesOnOneDayDTO> articlesOnOneDayDTOS = articleMapper.listOfArticlesOnOneDay();
        //浏览量
        List<UniqueViewDTO> uniqueViewDTOS = uniqueViewService.listOfUniqueViewDTO();

        //从缓存中找出访问量前5的文章 high to low score
        Map<Object, Double> reverseRangeWithScore = redisHandler.zReverseRangeWithScore(ARTICLE_VIEWS_COUNT_TAG, 0, 4);
        //文章排行
        List<ArticleViewsRankDTO> articleViewsRankDTOS = scoreMapBuild2RankDTO(reverseRangeWithScore);

        return BlogCountsInfoDTO.builder()
                //counts
                .viewsCount(viewsCount)
                .messagesCount(messageCount.intValue())
                .usersCount(usersCount.intValue())
                .articlesCount(articleCount.intValue())
                //list
                .categoryList(categoryDTOS)
                .tagList(tagDTOS)
                .articlesOnOneDayList(articlesOnOneDayDTOS)
                .uniqueViewDTOList(uniqueViewDTOS)
                .articleViewsRankList(articleViewsRankDTOS)
                .build();

    }

    /**
     * 查询所有用户相关区域分布
     * @param condition     前端传输的条件
     */
    @Override
    public List<UserAreaDTO> listOfUserAreas(ConditionVO condition) {
        List<UserAreaDTO> result = new LinkedList<>();
        Integer userType = condition.getType();
        if (StringUtils.isNull(userType)) {
            return null;
        }

        switch (Objects.requireNonNull(UserTypeEnum.getUserByType(userType), "未知的用户类型")) {
            case USER:
                //查询以注册用户的区域分布
                Object userArea = redisHandler.get(USER_AREA);
                if (StringUtils.isNotNull(userArea)) {
                     result = JSON.parseObject(((String) userArea), List.class);
                }
                return result;
            case VISITOR:
                //查询游客的区域分布
                Map<String , Object> map = redisHandler.hGetAll(VISITOR_AREA);
                if (StringUtils.isNotNull(map)) {
                    result = map.entrySet().stream()
                            .map(entry -> {
                                return UserAreaDTO.builder()
                                        .name(entry.getKey().toString())
                                        .value(Long.valueOf(entry.getValue().toString()))
                                        .build();
                            })
                            .collect(Collectors.toList());
                }
                return result;
        }
        return result;
    }

    /**
     * 获取我的描述
     * @return      描述
     */
    @Override
    public String getAboutInfo() {
        Object info = redisHandler.get(ABOUT);
        return Objects.nonNull(info) ? info.toString() : "";
    }

    /**
     * 获取博客的信息
     */
    @Override
    public BlogHomeInfoDTO getBlogHomeInfo() {
        //文章数量
        Integer articleCount = articleMapper.selectCount(new LambdaQueryWrapper<ArticleEntity>()
                .eq(ArticleEntity::getIsDelete, 0))
                .intValue();
        //分类总量
        Integer categoryCount = categoryMapper.selectCount(null).intValue();
        //标签总量
        Integer tagCount = tagMapper.selectCount(null).intValue();

        //总访问量
        Object cacheCount = redisHandler.get(BLOG_VIEWS_COUNT_TAG);
        String viewCount = Optional.ofNullable(cacheCount).orElse(0).toString();
        //网站配置
        WebsiteConfigVO websiteConfig = websiteConfigService.getWebsiteConfig();
        //页面
        List<PageDTO> pageDTOList = pageService.listPage();

        return new BlogHomeInfoDTO().builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewsCount(viewCount)
                .websiteConfig(websiteConfig)
                .pageList(pageDTOList)
                .build();
    }


    /**
     * 将从缓存中得到的{KEY:ArticleId, Value:Score} map改为List<ArticleViewsRankDTO>
     * @param articleScoreMap       缓存中的文章id和对应zset score
     * @return
     */
    private List<ArticleViewsRankDTO> scoreMapBuild2RankDTO(Map<Object, Double> articleScoreMap) {
        if (StringUtils.isNull(articleScoreMap) || articleScoreMap.isEmpty()) {
            return null;
        }
        //map遍历，将articleId传入数组中
        LinkedList<Integer> ids = new LinkedList<>();
        articleScoreMap.forEach((key, val) -> ids.add((Integer) key));

        //查询条件：in ids，得到信息:ArticleEntity{Id,Title}
        LambdaQueryWrapper<ArticleEntity> queryWrapper = new LambdaQueryWrapper<ArticleEntity>()
                //提取id和title即可
                .select(ArticleEntity::getId, ArticleEntity::getArticleTitle)
                .in(ArticleEntity::getId, ids);
        List<ArticleEntity> articles = articleMapper.selectList(queryWrapper);

        //转换
        return articles.stream()
                //ArticleEntity 2 ArticleViewsRankDTO
                .map(articleEntity ->
                        ArticleViewsRankDTO.builder()
                                .articleTitle(articleEntity.getArticleTitle())
                                //根据当前实体类的id去寻找map中的score
                                .viewsCount(articleScoreMap.get(articleEntity.getId()).intValue())
                                .build()
                )
                //sorted by ArticleViewsRankDTO#views
                .sorted(
                        Comparator.comparingInt(ArticleViewsRankDTO::getViewsCount)
                                //翻转
                                .reversed()
                )
                .collect(Collectors.toList());


    }
}
