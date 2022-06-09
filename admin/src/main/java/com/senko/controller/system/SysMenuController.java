package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.common.dto.ElementTreeLabelOptionDTO;
import com.senko.common.core.dto.MenuDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.MenuVO;
import com.senko.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author senko
 * @date 2022/5/2 22:29
 */
@Api("菜单模块")
@RestController
public class SysMenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 查询当前登录用户的可访问菜单
     *
     * @return 菜单名、路径、组件、图标、子菜单
     */
    @ApiOperation("查询用户的可选菜单")
    @GetMapping("/admin/user/menus")
    public AjaxResult<List<MenuForUserDTO>> listUserMenus() {
        List<MenuForUserDTO> result = menuService.listMenusForUser();
        for (MenuForUserDTO menuForUserDTO : result) {
            System.out.println(menuForUserDTO);
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询菜单后台
     * @param conditionVO   条件 菜单名
     * @return              菜单后台DTO 集合
     */
    @ApiOperation("查询后台菜单")
    @GetMapping("/admin/menus")
    public AjaxResult<List<MenuDTO>> listMenusBack(ConditionVO conditionVO) {
        List<MenuDTO> menuDTOList = menuService.listMenusBack(conditionVO);
        return AjaxResult.success(menuDTOList);
    }

    /**
     * 获取菜单图标集合
     * @return      图标名 集合
     */
    @ApiOperation("获取菜单图标集合")
    @GetMapping("/admin/menus/icons")
    public AjaxResult<List<String>> getMenuIcons() {
        List<String> icons = menuService.listMenuIcons();
        return AjaxResult.success("成功获取图标资源", icons);
    }

    /**
     * 根据菜单ID 删除其还有子菜单
     * @param id        菜单ID
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除菜单")
    @DeleteMapping("/admin/menus/{id}")
    public AjaxResult<?> deleteMenu(@PathVariable("id") Integer id) {
        menuService.deleteMenuByMenuId(id);
        return AjaxResult.success();
    }

    /**
     * 更新或新增菜单
     * @param menuVO    菜单表单
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("更新或新增菜单")
    @PostMapping("/admin/menus")
    public AjaxResult<?> saveOrUpdateMenu(@Valid @RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return AjaxResult.success();
    }

    /**
     * 查询 角色-菜单 集合，并返回符合ElementUI Tree所需data结构的数据
     */
    @ApiOperation("查询 角色菜单 集合")
    @GetMapping("/admin/role/menus")
    public AjaxResult<List<ElementTreeLabelOptionDTO>> listRoleMenuOption() {
        List<ElementTreeLabelOptionDTO> menuLabelList = menuService.listRoleMenuOption();
        return AjaxResult.success(menuLabelList);
    }
}
