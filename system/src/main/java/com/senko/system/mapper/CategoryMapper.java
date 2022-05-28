package com.senko.system.mapper;

import com.senko.common.common.dto.CategoryBackDTO;
import com.senko.common.common.dto.CategoryDTO;
import com.senko.common.common.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类Mapper
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

    /**
     *  分页查询分类后台列表
     */
    List<CategoryBackDTO> listCategoryBackDTO(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO condition);
}
