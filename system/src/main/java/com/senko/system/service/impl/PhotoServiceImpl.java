package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.PhotoBackDTO;
import com.senko.common.common.dto.PhotoDTO;
import com.senko.common.common.entity.PhotoAlbumEntity;
import com.senko.common.common.entity.PhotoEntity;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.common.vo.PhotoInfoVO;
import com.senko.common.common.vo.PhotoVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.system.mapper.PhotoMapper;
import com.senko.system.service.IPhotoAlbumService;
import com.senko.system.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 照片Service
 */
@Service("photoService")
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, PhotoEntity> implements IPhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private IPhotoAlbumService photoAlbumService;

    /**
     * 获取后台照片 分页集合
     * @param conditionVO    查询条件
     * @return               后台照片 分页结果
     */
    @Override
    public PageResult<PhotoBackDTO> listPhotoBackDTOList(ConditionVO conditionVO) {
        Page<PhotoEntity> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());

        Page<PhotoEntity> photoEntityPage = photoMapper.selectPage(page, new LambdaQueryWrapper<PhotoEntity>()
                .eq(Objects.nonNull(conditionVO.getIsDelete()), PhotoEntity::getIsDelete, conditionVO.getIsDelete())
                .eq(Objects.nonNull(conditionVO.getAlbumId()), PhotoEntity::getAlbumId, conditionVO.getAlbumId())
                .orderByDesc(PhotoEntity::getId)    // 按照id倒序
                .orderByDesc(PhotoEntity::getUpdateTime));


        List<PhotoBackDTO> photoBackDTOS = BeanCopyUtils.copyList(photoEntityPage.getRecords(), PhotoBackDTO.class);
        return new PageResult<>((int)photoEntityPage.getTotal(),photoBackDTOS);
    }

    /**
     * 上传图片到对应相册
     * @param photoVO   图片集合、相册ID、图片IDS
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadPhotos(PhotoVO photoVO) {
        List<PhotoEntity> photos = photoVO.getPhotoUrlList().stream()
                .map(photoUrl -> {
                    return PhotoEntity.builder()
                            .albumId(photoVO.getAlbumId())
                            .photoSrc(photoUrl)
                            .photoName(IdWorker.getIdStr())
                            .build();
                })
                .collect(Collectors.toList());
        this.saveBatch(photos);
    }

    /**
     * 更新图片信息
     * @param photoInfoVO 图片信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhoto(PhotoInfoVO photoInfoVO) {
        PhotoEntity photoEntity = BeanCopyUtils.copyObject(photoInfoVO, PhotoEntity.class);
        this.updateById(photoEntity);
    }

    /**
     * 逻辑删除图片
     * @param deleteVO 图片IDS，删除状态码 0:未删除 1:已删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void logicDeletePhoto(DeleteVO deleteVO) {
        //更新图片状态
        List<PhotoEntity> photoEntities = deleteVO.getIdList().stream()
                .map(id -> {
                    return PhotoEntity.builder()
                            .id(id)
                            .isDelete(deleteVO.getIsDelete())
                            .build();
                })
                .collect(Collectors.toList());
        this.updateBatchById(photoEntities);

        //如果想要恢复逻辑删除假时所在相册也被删除了，则对相册进行恢复
        if (deleteVO.getIsDelete().equals(CommonConstants.FALSE)) {
            //找到每张图片对应的相册
            List<PhotoAlbumEntity> albumEntities = photoMapper.selectList(new LambdaQueryWrapper<PhotoEntity>()
                            .select(PhotoEntity::getAlbumId)
                            .in(PhotoEntity::getId, deleteVO.getIdList())
                            .groupBy(PhotoEntity::getAlbumId))
                    .stream()
                    .map(photoEntity -> {
                        return PhotoAlbumEntity.builder()
                                .id(photoEntity.getAlbumId())
                                .isDelete(CommonConstants.FALSE)
                                .build();
                    })
                    .collect(Collectors.toList());
            photoAlbumService.updateBatchById(albumEntities);
        }
    }

    /**
     * 移动图片到指定相册
     * @param photoVO 图片IDS、相册ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void movePhotoToAlbum(PhotoVO photoVO) {
        List<PhotoEntity> photoEntityList = photoVO.getPhotoIdList().stream()
                .map(id -> {
                    return PhotoEntity.builder()
                            .id(id)
                            .albumId(photoVO.getAlbumId())
                            .build();
                })
                .collect(Collectors.toList());
        this.updateBatchById(photoEntityList);
    }

    /**
     * 获取相册对应的图片
     * @param albumId   相册ID
     * @return          图片集合
     */
    @Override
    public PhotoDTO listPhotoByAlbumId(Integer albumId) {
        PhotoAlbumEntity one = photoAlbumService.getOne(new LambdaQueryWrapper<PhotoAlbumEntity>()
                .eq(PhotoAlbumEntity::getId, albumId)
                .eq(PhotoAlbumEntity::getIsDelete, CommonConstants.FALSE)
                .eq(PhotoAlbumEntity::getStatus, 1));
        if (Objects.isNull(one)) {
            throw new ServiceException("该相册不存在");
        }
        // 查询照片
        Page<PhotoEntity> pageRequest = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        Page<PhotoEntity> pageResult = photoMapper.selectPage(pageRequest, new LambdaQueryWrapper<PhotoEntity>()
                .select(PhotoEntity::getPhotoSrc)
                .eq(PhotoEntity::getAlbumId, albumId)
                .eq(PhotoEntity::getIsDelete, CommonConstants.FALSE)
                .orderByDesc(PhotoEntity::getId));

        List<String> photoSrcList = pageResult.getRecords().stream()
                .map(PhotoEntity::getPhotoSrc)
                .collect(Collectors.toList());

        return PhotoDTO.builder()
                .photoAlbumName(one.getAlbumName())
                .photoAlbumCover(one.getAlbumCover())
                .photoList(photoSrcList)
                .build();
    }
}
