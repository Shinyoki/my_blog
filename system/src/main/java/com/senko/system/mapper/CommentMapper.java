package com.senko.system.mapper;

import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论Mapper
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

    /**
     * 通过条件查询符合的评论数量
     * @param conditionVO   条件
     * @return              数量
     */
    Long selectCountByConditionVO(@Param("condition") ConditionVO conditionVO);

    /**
     * 查询评论后台DTO 分页集合
     * @param conditionVO   条件
     * @return              评论后台DTO 集合
     */
    List<CommentBackDTO> listCommentBack(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO conditionVO);
}
