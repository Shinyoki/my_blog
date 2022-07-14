package com.senko.controller.system;

import com.senko.common.annotation.AccessLimit;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.GithubVO;
import com.senko.common.core.vo.QQLoginVO;
import com.senko.common.core.vo.UserVO;
import com.senko.system.service.IUserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 后台用户Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api(tags = "后台用户模块")
@RestController
public class SysUserAuthController {
    @Autowired
    private IUserAuthService userAuthService;

    /**
     * 查询后台用户 分页集合
     *
     * @param conditionVO 条件（用户名、登陆类型）
     * @return 后台用户 分页集合
     */
    @ApiOperation("查询后台用户集合")
    @GetMapping("/admin/users")
    public AjaxResult<PageResult<UserBackDTO>> listUserBack(ConditionVO conditionVO) {
        PageResult<UserBackDTO> userBackDTOList = userAuthService.listUserBack(conditionVO);
        return AjaxResult.success(userBackDTOList);
    }

    /**
     * QQ登录
     *
     * @param loginVO 登录信息
     * @return 登录后的用户
     */
    @ApiOperation("QQ登录")
    @PostMapping("/users/oauth/qq")
    public AjaxResult<UserLoginInfoDTO> qqLogin(@Valid @RequestBody QQLoginVO loginVO) {
        UserLoginInfoDTO userLoginInfoDTO = userAuthService.qqLogin(loginVO);
        return AjaxResult.success(userLoginInfoDTO);
    }

    /**
     * Github登录
     *
     * @param githubVO 登录信息
     * @return 登录后的用户
     */
    @ApiOperation("Github登录")
    @PostMapping("/users/oauth/github")
    public AjaxResult<UserLoginInfoDTO> githubLogin(@Valid @RequestBody GithubVO githubVO) {
        UserLoginInfoDTO userLoginInfoDTO = userAuthService.githubLogin(githubVO);
        return AjaxResult.success(userLoginInfoDTO);
    }

    /**
     * 发送邮箱验证码
     * @param username  邮箱地址
     */
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/users/code")
    public AjaxResult<?> sendCode(String username) {
        userAuthService.sendEmailValidCode(username);
        return AjaxResult.success("邮箱验证码发送成功");
    }

    /**
     * 注册用户
     * @param userVO    用户信息
     */
    @ApiOperation("用户注册")
    @PostMapping("/users/register")
    public AjaxResult<?> doRegister(@Valid @RequestBody UserVO userVO) {
        userAuthService.doRegister(userVO);
        return AjaxResult.success("注册成功");
    }
}
