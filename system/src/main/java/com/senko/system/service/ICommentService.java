package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.CommentBackDTO;
import com.senko.common.core.entity.CommentEntity;
import com.senko.common.core.vo.CommentIsReviewVO;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;
import java.util.Map;

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


}

