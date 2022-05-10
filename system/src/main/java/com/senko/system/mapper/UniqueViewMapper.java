package com.senko.system.mapper;


import com.senko.common.core.dto.UniqueViewDTO;
import com.senko.common.core.entity.UniqueViewEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface UniqueViewMapper extends BaseMapper<UniqueViewEntity> {

    /**
     * 找出起始时间相隔过程中的UniView集合
     *
     * 当参数为多个，且不是像Page这种Mybatis自己的实体类时，
     * 需要使用@Param将参数声明，否则无法正常映射。
     * @param beginDateTime
     * @param endDateTime
     */
    List<UniqueViewDTO> listOfUniqueViewDTO(@Param("beginDateTime") Date beginDateTime, @Param("endDateTime") Date endDateTime);
}
