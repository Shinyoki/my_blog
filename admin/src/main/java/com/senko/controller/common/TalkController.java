package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ITalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 说说Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("说说Controller")
@RestController
public class TalkController {
    @Autowired
    private ITalkService talkService;

    /**
     * 查询后台说说  分页集合
     * @param conditionVO   条件
     * @return              后台说说 分页集合
     */
    @ApiOperation("获取后台说说 分页集合")
    @GetMapping("/admin/talks")
    public AjaxResult<PageResult<TalkBackDTO>> listTalkBack(ConditionVO conditionVO) {
        PageResult<TalkBackDTO> talkBackDTOPageResult = talkService.listTalkBack(conditionVO);
        return AjaxResult.success(talkBackDTOPageResult);
    }

    /**
     * 删除说说 集合
     * @param talkIdList        说说ID 集合
     */
    @LogOperation(optType = OperationTypeConstants.REMOVE)
    @ApiOperation("删除说说 集合")
    @DeleteMapping("/admin/talks")
    public AjaxResult<?> deleteTalks(@RequestBody List<Integer> talkIdList) {
        talkService.removeBatchByIds(talkIdList);
        return AjaxResult.success();
    }
}
