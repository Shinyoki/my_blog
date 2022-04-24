package com.senko.system.mapper;

import com.senko.common.core.entity.PhotoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 照片
 * 
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Mapper
public interface PhotoMapper extends BaseMapper<PhotoEntity> {
	
}
