package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.RoleDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.RoleVO;
import com.senko.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色Controller
 * @author senko
 * @date 2022/5/26 9:46
 */
@Api("角色模块")
@RestController
public class SysRoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 查询角色列表
     * @param conditionVO       条件
     * @return                  角色  分页集合
     */
    @ApiOperation("查询角色列表")
    @GetMapping("/admin/roles")
    public AjaxResult<PageResult<RoleDTO>> listRoles(ConditionVO conditionVO) {
        PageResult<RoleDTO> roleDTOList = roleService.listRoles(conditionVO);
        return AjaxResult.success(roleDTOList);
    }

    /**
     * 删除角色 如果存在与角色绑定的用户，则失败
     * @param roleIdList       角色ID 集合
     */
    @LogOperation(optType = OperationTypeConstants.REMOVE)
    @ApiOperation("删除角色")
    @DeleteMapping("/admin/roles")
    public AjaxResult<?> deleteRoles(@RequestBody List<Integer> roleIdList) {
        roleService.deleteRoles(roleIdList);
        return AjaxResult.success();
    }

    /**
     * 新增或修改角色
     * @param roleVO    角色VO
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("新增或修改角色")
    @PostMapping("/admin/role")
    public AjaxResult<?> saveOrUpdateRole(@Valid @RequestBody RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return AjaxResult.success();
    }
}
