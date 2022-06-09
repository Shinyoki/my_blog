package com.senko.system.mapper;

import com.senko.common.common.dto.AlbumBackDTO;
import com.senko.common.common.entity.PhotoAlbumEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 相册
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Mapper
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbumEntity> {

    /**
     * 查询后台相册 分页集合
     * @param conditionVO   查询条件
     * @return              后台相册分页集合
     */
    List<AlbumBackDTO> listPhotoAlbumDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO conditionVO);
}
