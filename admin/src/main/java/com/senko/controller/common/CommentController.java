package com.senko.controller.common;


import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.CommentDTO;
import com.senko.common.common.vo.CommentVO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.vo.CommentIsReviewVO;
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
@Api(tags = "评论模块")
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
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除评论")
    @DeleteMapping("/admin/comments")
    public AjaxResult<?> deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return AjaxResult.success();
    }


    /**
     * 查询评论
     */
    @ApiOperation("查询评论")
    @GetMapping("/comments")
    public AjaxResult<PageResult<CommentDTO>> listComments(CommentVO commentVO) {
        PageResult<CommentDTO> commentDTOList = commentService.listComments(commentVO);
        return AjaxResult.success(commentDTOList);
    }

    /**
     * 添加评论
     */
    @LogOperation(optType = OperationTypeConstants.SAVE)
    @ApiOperation("添加评论")
    @PostMapping("/comments")
    public AjaxResult<?> saveComment(@Valid @RequestBody CommentVO commentVO) {
        commentService.saveComment(commentVO);
        return AjaxResult.success();
    }

    /**
     * 给评论点赞
     */
    @ApiOperation("给评论点赞")
    @PostMapping("/comments/{commentId}/like")
    public AjaxResult<?> likeComment(@PathVariable("commentId") Integer commentId) {
        commentService.likeComment(commentId);
        return AjaxResult.success();
    }

}
