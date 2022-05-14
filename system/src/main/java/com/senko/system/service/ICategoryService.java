package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.CategoryOptionDTO;
import com.senko.common.core.entity.CategoryEntity;
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
}

