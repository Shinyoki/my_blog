package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.AlbumBackDTO;
import com.senko.common.common.entity.PhotoAlbumEntity;
import com.senko.common.common.vo.PhotoAlbumVO;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;

/**
 * 相册
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IPhotoAlbumService extends IService<PhotoAlbumEntity> {

    /**
     * 查询后台相册 分页集合
     * @param conditionVO   查询条件
     * @return              后台相册分页集合
     */
    PageResult<AlbumBackDTO> listPhotoAlbumDTO(ConditionVO conditionVO);

    /**
     * 更新或修改相册
     * @param photoAlbumVO   相册信息
     */
    void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO);

    /**
     * 删除相册
     * @param id              相册id
     */
    void deletePhotoAlbumById(Integer id);

    /**
     * 根据AlbumId获取后台相册
     * @param albumId       相册Id
     * @return              后台相册
     */
    AlbumBackDTO getPhotoAlbumDTOById(Integer albumId);
}

