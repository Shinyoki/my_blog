package com.senko.system.service.impl;

import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.dto.*;
import com.senko.common.core.entity.ArticleEntity;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.*;
import com.senko.system.service.ISysBlogInfoService;
import com.senko.system.service.IUniqueViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static com.senko.common.constants.RedisConstants.*;
/**
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
        }
        //2.访问数量
        redisHandler.increment(RedisConstants.BLOG_VIEWS_COUNT_TAG, 1L);
        //3.来访者 记录
        redisHandler.sAdd(RedisConstants.UNIQUE_VISITOR, md5);
    }

    /**
     * 获取各种已有的访问量、以及分类等集合的统计
     */
    @Override
    public BlogCountsInfoDTO getBasicAdminInfo() {
        //访问量
        Integer views = (Integer) redisHandler.get(BLOG_VIEWS_COUNT_TAG);
        Integer integer = Optional.ofNullable(views).orElse(0);
        //留言数量
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
        List<ArticlesOnOneDayDTO> articles = articleMapper.listOfArticlesOnOneDay();
        //浏览量
        List<UniqueViewDTO> uniqueViewDTOS = uniqueViewService.listOfUniqueViewDTO();
        //TODO 还有待完善
        return null;
    }
}
