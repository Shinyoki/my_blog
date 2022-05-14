package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.dto.CategoryOptionDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.CategoryMapper;
import com.senko.common.core.entity.CategoryEntity;
import com.senko.system.service.ICategoryService;

import java.util.List;

/**
 * 分类服务
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ICategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 查询符合条件的分类集合
     * @param condition 条件
     */
    @Override
    public List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition) {
        LambdaQueryWrapper<CategoryEntity> query = new LambdaQueryWrapper<CategoryEntity>()
                .like(StringUtils.isNotBlank(condition.getKeywords()),
                        CategoryEntity::getCategoryName,
                        condition.getKeywords())
                .orderByDesc(CategoryEntity::getId);
        List<CategoryEntity> categories = categoryMapper.selectList(query);

        return BeanCopyUtils.copyList(categories, CategoryOptionDTO.class);
    }
}
