package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.PageDTO;
import com.senko.common.common.vo.PageVO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.system.service.IPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 页面
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api(tags = "页面模块")
@RestController
public class PageController {
    @Autowired
    private IPageService pageService;

    /**
     * 获取页面 集合
     */
    @ApiOperation(value = "获取页面")
    @GetMapping("/admin/pages")
    public AjaxResult<List<PageDTO>> listPage() {
        List<PageDTO> pageResult = pageService.listPage();
        return AjaxResult.success(pageResult);
    }

    /**
     * 保存或更新页面
     * @param pageVO   页面信息VO
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新页面")
    @PostMapping("/admin/pages")
    public AjaxResult<?> saveOrUpdatePage(@Valid @RequestBody PageVO pageVO) {
        pageService.saveOrUpdatePage(pageVO);
        return AjaxResult.success();
    }

    /**
     * 删除页面
     * @param pageId    页面id
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation(value = "删除页面")
    @GetMapping("/admin/pages/{pageId}")
    public AjaxResult<?> deletePage(@PathVariable("pageId") Integer pageId) {
        pageService.deletePageById(pageId);
        return AjaxResult.success();
    }
}
