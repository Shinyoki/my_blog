package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.MessageBackDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.common.vo.MessageIsReviewVO;
import com.senko.system.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 留言（弹幕）Controller
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@RestController
@Api("留言模块")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    /**
     * 查询后台留言信息集合
     * @param conditionVO       条件
     * @return                  后台留言 分页集合
     */
    @ApiOperation("查询后台留言集合")
    @GetMapping("/admin/messages")
    public AjaxResult<PageResult<MessageBackDTO>> listMessageBack(ConditionVO conditionVO) {
        PageResult<MessageBackDTO> messageBackDTOList = messageService.listMessageBack(conditionVO);
        return AjaxResult.success(messageBackDTOList);
    }

    /**
     * 更新留言审核状态
     * @param messageIsReviewVO id集合、希望改为的审核状态
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新留言审核状态")
    @PutMapping("/admin/messages/review")
    public AjaxResult<?> updateMessagesReview(@Valid @RequestBody MessageIsReviewVO messageIsReviewVO) {
        messageService.updateMessagesReview(messageIsReviewVO);
        return AjaxResult.success();
    }

    @LogOperation(optType = OperationTypeConstants.REMOVE)
    @ApiOperation("删除留言")
    @DeleteMapping("/admin/messages")
    public AjaxResult<?> deleteMessages(@RequestBody List<Integer> messageIdList) {
        messageService.removeByIds(messageIdList);

        return AjaxResult.success();
    }

}
