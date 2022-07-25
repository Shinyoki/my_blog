package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.PhotoBackDTO;
import com.senko.common.common.dto.PhotoDTO;
import com.senko.common.common.entity.PhotoEntity;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.common.vo.PhotoInfoVO;
import com.senko.common.common.vo.PhotoVO;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;

/**
 * 照片
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IPhotoService extends IService<PhotoEntity> {

    /**
     * 获取后台照片 分页集合
     * @param conditionVO    查询条件
     * @return               后台照片 分页结果
     */
    PageResult<PhotoBackDTO> listPhotoBackDTOList(ConditionVO conditionVO);

    /**
     * 上传图片到对应相册
     * @param photoVO   图片集合、相册ID、图片IDS
     */
    void uploadPhotos(PhotoVO photoVO);

    /**
     * 更新图片信息
     * @param photoInfoVO 图片信息
     */
    void updatePhoto(PhotoInfoVO photoInfoVO);

    /**
     * 逻辑删除图片
     * @param deleteVO 图片IDS，删除状态码 0:未删除 1:已删除
     */
    void logicDeletePhoto(DeleteVO deleteVO);

    /**
     * 移动图片到指定相册
     * @param photoVO 图片IDS、相册ID
     */
    void movePhotoToAlbum(PhotoVO photoVO);

    /**
     * 获取相册对应的图片
     * @param albumId   相册ID
     * @return          图片集合
     */
    PhotoDTO listPhotoByAlbumId(Integer albumId);
}

