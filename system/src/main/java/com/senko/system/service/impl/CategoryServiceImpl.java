package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senko.common.common.dto.CategoryDTO;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CategoryBackDTO;
import com.senko.common.common.dto.CategoryOptionDTO;
import com.senko.common.common.entity.ArticleEntity;
import com.senko.common.common.vo.CategoryVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.CategoryMapper;
import com.senko.common.common.entity.CategoryEntity;
import com.senko.system.service.ICategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 分类服务
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ICategoryService {

    private CategoryMapper categoryMapper;

    private ArticleMapper articleMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
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

    /**
     * 查询分类后台数据
     * @param condition  条件
     */
    @Override
    public PageResult<CategoryBackDTO> listCategoryBack(ConditionVO condition) {
        //分类的数量
        Long count = categoryMapper.selectCount(new LambdaQueryWrapper<CategoryEntity>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), CategoryEntity::getCategoryName, condition.getKeywords()));
        if (count == 0) {
            return new PageResult<>();
        }
        //分页查询
        List<CategoryBackDTO> categoryBackDTOList = categoryMapper.listCategoryBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<CategoryBackDTO>(count.intValue(), categoryBackDTOList);
    }

    /**
     * 删除分类（如果分类下存在文章，会删除失败）
     * @param categoryIdList 分类id 集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategories(List<Integer> categoryIdList) {
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<ArticleEntity>()
                .in(CollectionUtils.isNotEmpty(categoryIdList), ArticleEntity::getCategoryId, categoryIdList));
        if (count > 0) {
            throw new ServiceException("删除失败，该分类下存在文章！");
        }

        categoryMapper.deleteBatchIds(categoryIdList);
    }

    /**
     * 添加或修改分类
     *
     * @param categoryVO id & categoryName
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateCategory(CategoryVO categoryVO) {
        //搜索 与categoryName匹配的分类
        CategoryEntity result = categoryMapper.selectOne(new LambdaQueryWrapper<CategoryEntity>()
                .select(CategoryEntity::getId)
                .eq(CategoryEntity::getCategoryName, categoryVO.getCategoryName()));
        //存在并且id匹配，抛出异常
        if (Objects.nonNull(result) && result.getId().equals(categoryVO.getId())) {
            throw new ServiceException("该分类已存在");
        }
        //找到名，但是id不同，则根据id修改其为新的名
        CategoryEntity newCate = CategoryEntity.builder()
                .id(categoryVO.getId())
                .categoryName(categoryVO.getCategoryName())
                .build();
        this.saveOrUpdate(newCate);
    }

    @Override
    public PageResult<CategoryDTO> listCategoryDTO(ConditionVO conditionVO) {
        Long count = categoryMapper.selectCount(null);
        List<CategoryDTO> res = categoryMapper.selectCategoriesWithArticleCounts(PageUtils.getLimitCurrent(), PageUtils.getSize());
        return new PageResult<>(count.intValue(), res);
    }
}
