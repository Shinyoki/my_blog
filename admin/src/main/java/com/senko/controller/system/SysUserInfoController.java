package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserOnlineDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.UserIsDisableVO;
import com.senko.common.core.vo.UserRoleVO;
import com.senko.common.utils.page.PageUtils;
import com.senko.system.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用户信息Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("用户信息模块")
@RestController
public class SysUserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 更新用户的禁用状态
     *
     * @param isDisableVO 用户信息id、禁用态
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新用户的禁用状态")
    @PutMapping("/admin/users/disable")
    public AjaxResult<?> updateUserIsDisable(@Valid @RequestBody UserIsDisableVO isDisableVO) {
        userInfoService.updateUserIsDisable(isDisableVO);
        return AjaxResult.success();
    }

    /**
     * 查询在线用户
     * @param conditionVO   条件
     * @return              在线用户 分页集合
     */
    @ApiOperation("查询在线用户")
    @GetMapping("/admin/users/online")
    public AjaxResult<PageResult<UserOnlineDTO>> listUserOnline(ConditionVO conditionVO) {
        PageResult<UserOnlineDTO> userOnlineDTOPageResult = userInfoService.listUserOnline(conditionVO);
        return AjaxResult.success(userOnlineDTOPageResult);
    }


    @ApiOperation("关于我")
    @GetMapping("/about")
    public AjaxResult<?> aboutMe() {
        return AjaxResult.success("哈哈哈");
    }

    /**
     * 更新用户的角色列表
     * @param userRoleVO    用户id、用户名、角色id集合
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新用户的角色")
    @PutMapping("/admin/users/role")
    public AjaxResult<?> updateUserRole(@Valid @RequestBody UserRoleVO userRoleVO) {
        userInfoService.updateUserRole(userRoleVO);
        return AjaxResult.success();
    }


    /**
     * 踢在线用户下线
     * @param userInfoId    userInfoId
     */
    @ApiOperation("踢在线用户下线")
    @DeleteMapping("/admin/users/{userInfoId}/online")
    public AjaxResult<?> kickOnlineUser(@PathVariable("userInfoId") Integer userInfoId) {
        userInfoService.kickOnlineUser(userInfoId);
        return AjaxResult.success();
    }
}
