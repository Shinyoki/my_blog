package com.senko.system.service;

import com.senko.common.core.dto.BlogCountsInfoDTO;

/**
 * 博客相关信息 服务
 *
 * @author senko
 * @date 2022/5/9 13:01
 */
public interface ISysBlogInfoService {
    /**
     * 记录当前访问
     */
    void recordCurView();

    /**
     * 获取各种已有的访问量、以及分类等集合的统计
     */
    BlogCountsInfoDTO getBasicAdminInfo();
}
