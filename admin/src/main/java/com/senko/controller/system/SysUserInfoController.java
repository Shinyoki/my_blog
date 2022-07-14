package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserOnlineDTO;
import com.senko.common.core.vo.*;
import com.senko.system.service.IUserAuthService;
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
@Api(tags = "用户信息模块")
@RestController
public class SysUserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserAuthService userAuthService;

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
     *
     * @param conditionVO 条件
     * @return 在线用户 分页集合
     */
    @ApiOperation("查询在线用户")
    @GetMapping("/admin/users/online")
    public AjaxResult<PageResult<UserOnlineDTO>> listUserOnline(ConditionVO conditionVO) {
        PageResult<UserOnlineDTO> userOnlineDTOPageResult = userInfoService.listUserOnline(conditionVO);
        return AjaxResult.success(userOnlineDTOPageResult);
    }


    /**
     * 更新用户的角色列表
     *
     * @param userRoleVO 用户id、用户名、角色id集合
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
     *
     * @param userInfoId userInfoId
     */
    @ApiOperation("踢在线用户下线")
    @DeleteMapping("/admin/users/{userInfoId}/online")
    public AjaxResult<?> kickOnlineUser(@PathVariable("userInfoId") Integer userInfoId) {
        userInfoService.kickOnlineUser(userInfoId);
        return AjaxResult.success();
    }

    /**
     * 更新用户信息
     *
     * @param userInfoVO 用户信息
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新用户的信息")
    @PutMapping("/users/info")
    public AjaxResult<?> updateUserInfo(@Valid @RequestBody UserinfoVO userInfoVO) {
        userInfoService.updateUserInfo(userInfoVO);
        return AjaxResult.success();
    }

    /**
     * 更新用户密码
     *
     * @param userPasswordVO 用户id、用户名、密码
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新用户密码")
    @PutMapping("/admin/users/password")
    public AjaxResult<?> updateUserPassword(@Valid @RequestBody UserPasswordVO userPasswordVO) {
        userAuthService.updateUserPassword(userPasswordVO);
        return AjaxResult.success();
    }

    /**
     * 绑定用户邮箱
     * @param userEmailVO  邮箱 验证码
     */
    @ApiOperation("绑定用户邮箱")
    @PostMapping("/users/email")
    public AjaxResult<?> bindUserEmail(@Valid @RequestBody UserEmailVO userEmailVO) {
        userInfoService.bindUserEmail(userEmailVO);
        return AjaxResult.success();
    }
}
