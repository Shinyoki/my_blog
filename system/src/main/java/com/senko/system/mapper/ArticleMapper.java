package com.senko.system.mapper;

import com.senko.common.core.dto.ArticleBackDTO;
import com.senko.common.core.dto.ArticleViewsRankDTO;
import com.senko.common.core.dto.ArticlesOnOneDayDTO;
import com.senko.common.core.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    /**
     * 得到某一天里的文章数量
     */
    List<ArticlesOnOneDayDTO> listOfArticlesOnOneDay();


    /**
     * 统计符合条件的后台文章数量
     * @param condition   参数VO
     * @return              符合条件的文章数量
     */
    Integer countArticleBacks(@Param("condition") ConditionVO condition);

    List<ArticleBackDTO> listArticleBacks(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO conditionVO);
}
