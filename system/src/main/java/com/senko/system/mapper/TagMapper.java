package com.senko.system.mapper;

import com.senko.common.common.dto.TagBackDTO;
import com.senko.common.common.dto.TagCountDTO;
import com.senko.common.common.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签Mapper
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {

    /**
     * 以articleId查找该文章所绑定的tag标签
     * @param articleId  文章id
     * @return           与该文章绑定的tags
     */
    List<String> listTagNameByArticleId(Integer articleId);

    /**
     * 查询后台标签
     *
     * @param conditionVO   条件
     * @return              后台标签 集合
     */
    List<TagBackDTO> listTagBackDTO(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO conditionVO);

    List<TagCountDTO> listTagCountDTO();

}
