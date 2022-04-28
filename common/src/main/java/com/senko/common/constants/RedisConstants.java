package com.senko.common.constants;

/**
 * 将需要被redis缓存的字段分类
 * 以及存储和Redis相关的常量
 *
 * @author senko
 * @date 2022/4/28 19:49
 */
public class RedisConstants {

    //===============TAG================//

    /**
     * 邮箱验证码过期时间
     */
    public static final long CODE_EXPIRE_TIME = 15 * 60;

    /**
     * 博客 浏览量
     */
    public static final String BLOG_VIEWS_COUNT_TAG = "blog_views_count";

    /**
     * 文章 浏览数量
     */
    public static final String ARTICLE_VIEWS_COUNT_TAG = "article_views_count";

    /**
     * 文章 点赞数量
     */
    public static final String ARTICLE_LIKE_COUNT_TAG = "article_like_count";

    /**
     * 说说 点赞量
     */
    public static final String TALK_LIKE_COUNT_TAG = "talk_like_count";

    /**
     * 评论 点赞量
     */
    public static final String COMMENT_LIKE_COUNT_TAG = "comment_like_count";


    //=================前缀=============//
    //一般存储为 前缀+userInfo#id  ==> article_user_lke:userInfo#id

    /**
     * 验证码 前缀
     */
    public static final String CODE_KEY = "code:";

    /**
     * 用户点赞过的 文章
     */
    public static final String ARTICLE_USER_LIKE = "article_user_like:";

    /**
     * 用户点赞过的 说说
     */
    public static final String TALK_USER_LIKE = "talk_user_like:";

    /**
     * 用户点赞过的 评论
     */
    public static final String COMMENT_USER_LIKE = "comment_user_like:";


    //==================常量=================//

    /**
     * 网站配置
     */
    public static final String WEBSITE_CONFIG = "website_config";

    /**
     * 用户 所在地区
     */
    public static final String USER_AREA = "user_area";

    /**
     * 访客 所在地区
     */
    public static final String VISITOR_AREA = "visitor_area";

    /**
     * 文章页面的 封面
     */
    public static final String PAGE_COVER = "page_cover";

    /**
     * 关于
     */
    public static final String ABOUT = "about";

    /**
     * 单独访客
     */
    public static final String UNIQUE_VISITOR = "unique_visitor";
}
