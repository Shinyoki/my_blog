package com.senko.controller.system;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IUserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 后台用户Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("后台用户模块")
@RestController
public class SysUserAuthController {
    @Autowired
    private IUserAuthService userAuthService;

    /**
     * 查询后台用户 分页集合
     * @param conditionVO       条件（用户名、登陆类型）
     * @return                  后台用户 分页集合
     */
    @ApiOperation("查询后台用户集合")
    @GetMapping("/admin/users")
    public AjaxResult<PageResult<UserBackDTO>> listUserBack(ConditionVO conditionVO) {
        PageResult<UserBackDTO> userBackDTOList = userAuthService.listUserBack(conditionVO);
        return AjaxResult.success(userBackDTOList);
    }
}
