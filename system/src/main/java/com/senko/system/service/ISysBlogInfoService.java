package com.senko.system.service;

import com.senko.common.common.dto.BlogCountsInfoDTO;
import com.senko.common.common.dto.UserAreaDTO;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;

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

    /**
     * 查询所有用户相关区域分布
     * @param condition     前端传输的条件
     */
    List<UserAreaDTO> listOfUserAreas(ConditionVO condition);

    /**
     * 获取我的描述
     * @return      描述
     */
    String getAboutInfo();
}
