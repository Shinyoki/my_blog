package com.senko.system.mapper;

import com.senko.common.core.dto.CategoryDTO;
import com.senko.common.core.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

    /**
     * 获取目录DTO
     */
    List<CategoryDTO> listOfCategoryDTO();

}
