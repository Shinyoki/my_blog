package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.vo.UserIsDisableVO;
import com.senko.common.core.vo.UserRoleVO;
import com.senko.system.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户信息Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("用户信息模块")
@RestController
public class UserInfoController {

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
}
