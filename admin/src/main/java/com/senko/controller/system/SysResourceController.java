package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.common.dto.ElementTreeLabelOptionDTO;
import com.senko.common.core.dto.ResourceDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.ResourceVO;
import com.senko.system.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 资源Controller
 *
 * @author senko
 * @date 2022/5/25 20:46
 */
@Api(tags = "资源API")
@RestController
public class SysResourceController {
    @Autowired
    private IResourceService resourceService;

    /**
     * 获取后台资源列集合
     * <pre>
     * [
     *  模块1: {
     *      xxx...
     *      childList
     *  },
     *  模块2: {
     *      xxx...
     *      childList
     *  },
     *  ...
     * ]
     * </pre>
     * @param conditionVO       查询条件
     * @return                  资源列表
     */
    @ApiOperation("获取资源操作列表")
    @GetMapping("/admin/resources")
    public AjaxResult<List<ResourceDTO>> listResourcesBack(ConditionVO conditionVO) {
        List<ResourceDTO> resourceDTOList = resourceService.listResourcesBack(conditionVO);
        return AjaxResult.success(resourceDTOList);
    }

    /**
     * 新增或修改资源
     * @param resourceVO    资源信息
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("新增或修改资源")
    @PostMapping("/admin/resources")
    public AjaxResult<?> saveOrUpdateResource(@Valid @RequestBody ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return AjaxResult.success();
    }

    /**
     * 删除资源
     * @param resourceId    资源ID
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除资源")
    @DeleteMapping("/admin/resources/{resourceId}")
    public AjaxResult<?> deleteResource(@PathVariable("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return AjaxResult.success();
    }

    /**
     * 查询 角色-资源 集合
     * @return 角色-资源 集合：并返回符合ElementUI Tree所需data结构的数据
     */
    @ApiOperation("查询 角色-资源 集合")
    @GetMapping("/admin/role/resources")
    public AjaxResult<List<ElementTreeLabelOptionDTO>> listRoleResourceOption() {
        List<ElementTreeLabelOptionDTO> optionDTOList = resourceService.listRoleResourceOption();
        return AjaxResult.success(optionDTOList);
    }
}
