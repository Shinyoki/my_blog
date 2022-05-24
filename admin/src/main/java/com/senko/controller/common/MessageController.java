package com.senko.controller.common;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.MessageBackDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
