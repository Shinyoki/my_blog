package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.FriendLinkDTO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IFriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 友链模块
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api(tags = "友链模块")
@RestController
public class FriendLinkController {
    @Autowired
    private IFriendLinkService friendLinkService;

    /**
     * 查看所有友链
     * @return  友链 分页集合
     */
    @ApiOperation(value = "查看所有友链列表")
    @GetMapping("/links")
    public AjaxResult listFriendLinks() {
        ConditionVO emptyCondition = new ConditionVO();
        return AjaxResult.success(friendLinkService.listFriendLinkDTO(emptyCondition));
    }

    /**
     * 查看友链 分页集合
     * @param condition 条件
     * @return          友链列表
     */
    @ApiOperation(value = "查看友链列表")
    @GetMapping("/admin/links")
    public AjaxResult<PageResult<FriendLinkDTO>> listFriendLinkDTO(ConditionVO condition) {
        return AjaxResult.success(friendLinkService.listFriendLinkDTO(condition));
    }

    /**
     * 添加或修改友链
     * @param friendLinkDTO 友链信息
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation(value = "添加或修改友链")
    @PostMapping("/admin/links")
    public AjaxResult<?> saveOrUpdateFriendLink(@Valid @RequestBody FriendLinkDTO friendLinkDTO) {
        friendLinkService.saveOrUpdateFriendLink(friendLinkDTO);
        return AjaxResult.success();
    }

    /**
     * 删除友链
     * @param linkIdList    友链id集合
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除友链")
    @DeleteMapping("/admin/links")
    public AjaxResult<?> deleteFriendLink(@RequestBody List<Integer> linkIdList) {
        friendLinkService.removeByIds(linkIdList);
        return AjaxResult.success();
    }
}
