package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CategoryBackDTO;
import com.senko.common.common.dto.CategoryOptionDTO;
import com.senko.common.common.vo.CategoryVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * 根据keywords去搜索分类
     * @param condition keywords: categoryName
     * @return          categoryList
     */
    @ApiOperation("文章分类搜索")
    @GetMapping("/admin/categories/search")
    public AjaxResult<List<CategoryOptionDTO>> listCategoriesBySearch(ConditionVO condition) {
        return AjaxResult.success(categoryService.listCategoriesBySearch(condition));
    }


    /**
     * 拆寻分类后台数据
     * @param condition keywords: categoryName
     * @return          分类id、名，绑定的文章数量)
     */
    @ApiOperation("查询后台分类列表")
    @GetMapping("/admin/categories")
    public AjaxResult<PageResult<CategoryBackDTO>> listCategoryBack(ConditionVO condition) {
        return AjaxResult.success(categoryService.listCategoryBack(condition));
    }

    /**
     * 添加或修改分类
     * @param categoryVO id & categoryName
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("添加或修改分类")
    @PostMapping("/admin/categories")
    public AjaxResult<?> saveOrUpdateCategory(@Valid @RequestBody CategoryVO categoryVO) {
        categoryService.saveOrUpdateCategory(categoryVO);
        return AjaxResult.success();
    }

    /**
     * 删除分类
     * @param categoryIdList category ids
     */
    @LogOperation(optType = OperationTypeConstants.REMOVE)
    @ApiOperation("删除分类")
    @DeleteMapping("/admin/categories")
    public AjaxResult<?> deleteCategories(@RequestBody List<Integer> categoryIdList) {
        categoryService.deleteCategories(categoryIdList);
        return AjaxResult.success();
    }


}

