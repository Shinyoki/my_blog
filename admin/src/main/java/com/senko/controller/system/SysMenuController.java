package com.senko.controller.system;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.MenuDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IMenuService;
import com.senko.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author senko
 * @date 2022/5/2 22:29
 */
@Api("菜单模块")
@RestController
@RequestMapping("/menus")
public class SysMenuController {
    //TODO 用户可访菜单
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    /**
     * 查询当前登录用户的可访问菜单
     * @return          菜单名、路径、组件、图标、子菜单
     */
    @ApiOperation("查询用户的可选菜单")
    @GetMapping("/user/list")
    public AjaxResult<List<MenuForUserDTO>> listMenus() {
        List<MenuForUserDTO> result = menuService.listMenusForUser();
        System.out.println("返回数据:");
        for (MenuForUserDTO menuForUserDTO : result) {
            System.out.println(menuForUserDTO);
        }
        return AjaxResult.success(result);
    }


}
