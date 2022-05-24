package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.CategoryBackDTO;
import com.senko.common.core.dto.CategoryOptionDTO;
import com.senko.common.core.entity.CategoryEntity;
import com.senko.common.core.vo.CategoryVO;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;
import java.util.Map;

/**
 * 分类服务
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface ICategoryService extends IService<CategoryEntity> {

    /**
     * 查询符合条件的分类集合
     * @param condition 条件
     */
    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition);

    /**
     * 查询分类后台数据
     * @param condition  条件
     */
    PageResult<CategoryBackDTO> listCategoryBack(ConditionVO condition);

    /**
     * 删除分类 （如果分类下存在文章，会删除失败）
     * @param categoryIdList 分类id 集合
     */
    void deleteCategories(List<Integer> categoryIdList);

    /**
     * 添加或修改分类
     * @param categoryVO id & categoryName
     */
    void saveOrUpdateCategory(CategoryVO categoryVO);
}

