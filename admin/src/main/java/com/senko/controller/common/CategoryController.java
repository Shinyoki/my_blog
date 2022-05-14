package com.senko.controller.common;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.CategoryOptionDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章分类接口
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api("分类模块")
@RestController
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("文章分类搜索")
    @GetMapping("/admin/categories/search")
    public AjaxResult<List<CategoryOptionDTO>> listCategoriesBySearch(ConditionVO condition) {
        return AjaxResult.success(categoryService.listCategoriesBySearch(condition));
    }
}
