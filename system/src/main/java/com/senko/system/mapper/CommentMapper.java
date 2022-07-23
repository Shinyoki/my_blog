package com.senko.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.dto.CommentDTO;
import com.senko.common.common.dto.ReplyCountDTO;
import com.senko.common.common.dto.ReplyDTO;
import com.senko.common.common.entity.CommentEntity;
import com.senko.common.common.vo.CommentVO;
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

    /**
     * 查询评论
     */
    List<CommentDTO> listComments(@Param("current") Long limitCurrent, @Param("size") Long size,@Param("commentVO") CommentVO commentVO);

    /**
     * 根据一级评论搜索其回复
     *
     * 根据parent id分割分组，一个parent下超过四个的回复就忽略
     * ROW NUMBER () OVER (PARTITION BY parent_id ORDER BY create_time) AS row_number
     * https://www.cnblogs.com/icebutterfly/archive/2009/08/05/1539657.html
     */
    List<ReplyDTO> listReplies(@Param("commentIdList") List<Integer> commentIdList);

    /**
     * 根据评论id查询回复总量
     *
     * @param commentIdList 评论id集合
     * @return 回复数量
     */
    List<ReplyCountDTO> listReplyCountByCommentId(@Param("commentIdList") List<Integer> commentIdList);

}
