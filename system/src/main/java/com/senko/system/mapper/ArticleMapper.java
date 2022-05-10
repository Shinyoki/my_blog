package com.senko.system.mapper;

import com.senko.common.core.dto.ArticleViewsRankDTO;
import com.senko.common.core.dto.ArticlesOnOneDayDTO;
import com.senko.common.core.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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


}
