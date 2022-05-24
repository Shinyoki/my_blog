package com.senko.controller.common;


import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.CommentBackDTO;
import com.senko.common.core.vo.CommentIsReviewVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  评论
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@RestController
@Api("评论Controller")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    /**
     * 查询评论后台DTO 分页集合
     * @param conditionVO   条件
     * @return              评论后台DTO 分页集合
     */
    @ApiOperation("查询后台评论")
    @GetMapping("/admin/comments")
    public AjaxResult<PageResult<CommentBackDTO>> listCommentBack(ConditionVO conditionVO) {
        PageResult<CommentBackDTO> commentBackDTOPageResult = commentService.listCommentBack(conditionVO);
        return AjaxResult.success(commentBackDTOPageResult);
    }

    /**
     * 修改评论的审核状态
     * @param isReviewVO    评论id集合、需要被改为的状态
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新审核状态")
    @PutMapping("/admin/comments/review")
    public AjaxResult<?> updateCommentsIsReview(@Valid @RequestBody CommentIsReviewVO isReviewVO) {
        commentService.updateCommentsIsReview(isReviewVO);
        return AjaxResult.success();
    }

    /**
     * 删除评论
     * @param commentIdList     评论ID 集合
     */
    @LogOperation(optType = OperationTypeConstants.REMOVE)
    @ApiOperation("删除评论")
    @DeleteMapping("/admin/comments")
    public AjaxResult<?> deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return AjaxResult.success();
    }

}
