package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.CommentDTO;
import com.senko.common.common.dto.ReplyDTO;
import com.senko.common.common.vo.CommentVO;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.entity.CommentEntity;
import com.senko.common.common.vo.CommentIsReviewVO;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;

/**
 * 评论Service
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
public interface ICommentService extends IService<CommentEntity> {

    /**
     * 查询评论后台DTO 分页集合
     * @param conditionVO   条件
     * @return              评论后台DTO 分页集合
     */
    PageResult<CommentBackDTO> listCommentBack(ConditionVO conditionVO);

    /**
     * 修改评论的审核状态
     * @param isReviewVO    评论id集合、需要被改为的状态
     */
    void updateCommentsIsReview(CommentIsReviewVO isReviewVO);

    /**
     * 查询评论
     */
    PageResult<CommentDTO> listComments(CommentVO commentVO);

    /**
     * 添加评论
     */
    void saveComment(CommentVO commentVO);

    /**
     * 给评论点赞
     */
    void likeComment(Integer commentId);

    /**
     * 查询评论下的五个回复
     * @param commentId
     * @return
     */
    List<ReplyDTO> listCommentRepliesById(Integer commentId);
}

