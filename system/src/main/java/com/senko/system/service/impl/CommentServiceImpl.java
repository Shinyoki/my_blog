package com.senko.system.service.impl;

import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.vo.CommentIsReviewVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.CommentMapper;
import com.senko.common.common.entity.CommentEntity;
import com.senko.system.service.ICommentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论后台DTO 分页集合
     * @param conditionVO   条件
     * @return              评论后台DTO 分页集合
     */
    @Override
    public PageResult<CommentBackDTO> listCommentBack(ConditionVO conditionVO) {
        Long count = commentMapper.selectCountByConditionVO(conditionVO);
        if (count == 0) {
            return new PageResult<>();
        }

        List<CommentBackDTO> commentBackDTOList = commentMapper.listCommentBack(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<CommentBackDTO>(count.intValue(),commentBackDTOList);
    }

    /**
     * 修改评论的审核状态
     * @param isReviewVO    评论id集合、需要被改为的状态
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommentsIsReview(CommentIsReviewVO isReviewVO) {
        List<CommentEntity> comments = isReviewVO.getIdList().stream().map(id -> {
                    return CommentEntity.builder()
                            .id(id)
                            .isReview(isReviewVO.getIsReview())
                            .build();
                })
                .collect(Collectors.toList());
        this.updateBatchById(comments);
    }


}
