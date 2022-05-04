package com.senko.controller.test;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.test.TestUser;
import com.senko.system.service.IMenuService;
import com.senko.system.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author senko
 * @date 2022/4/29 22:37
 */
@RestController
@RequestMapping("/test")
public class TestController {
    //TODO 用户可访菜单
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    /**
     * 查询当前登录用户的可访问菜单
     *
     * @return          菜单名、路径、组件、图标、子菜单
     */
    @ApiOperation("查询用户的可选菜单")
    @GetMapping("/menus/user/list")
    public AjaxResult<List<MenuForUserDTO>> listMenus() {
        return AjaxResult.success(menuService.listMenusForUser());
    }

    /**
     * @RequestParam用来处理 Content-Type 为 application/x-www-form-urlencoded 编码的内容
     * ，Content-Type默认为该属性。@RequestParam也可用于其它类型的请求，例如：POST、DELETE等请求。
     * <a href="https://blog.csdn.net/weixin_38004638/article/details/99655322">可选参数</a>
     */
    @PostMapping("/post1")
    public TestUser post(@RequestBody TestUser user) {
        return user;
    }



    @GetMapping("/ping")
    public AjaxResult ping() {
        return AjaxResult.success("pong!");
    }

}


