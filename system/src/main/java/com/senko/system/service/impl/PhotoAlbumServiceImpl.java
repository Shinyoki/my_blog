package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.senko.common.common.dto.AlbumBackDTO;
import com.senko.common.common.entity.PhotoEntity;
import com.senko.common.common.vo.PhotoAlbumVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.system.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.PhotoAlbumMapper;
import com.senko.common.common.entity.PhotoAlbumEntity;
import com.senko.system.service.IPhotoAlbumService;

import java.util.List;
import java.util.Objects;

/**
 * 相册Serivce
 */
@Service("photoAlbumService")
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbumEntity> implements IPhotoAlbumService {

    @Autowired
    private PhotoAlbumMapper albumMapper;

    @Autowired
    private PhotoMapper photoMapper;
    /**
     * 查询后台相册 分页集合
     * @param conditionVO   查询条件
     * @return              后台相册分页集合
     */
    @Override
    public PageResult<AlbumBackDTO> listPhotoAlbumDTO(ConditionVO conditionVO) {
        //查询相册内未被逻辑删除的图片数量
        Long count = albumMapper.selectCount(new LambdaQueryWrapper<PhotoAlbumEntity>()
                .like(Objects.nonNull(conditionVO.getKeywords()), PhotoAlbumEntity::getAlbumName, conditionVO.getKeywords())
                .eq(PhotoAlbumEntity::getIsDelete, CommonConstants.FALSE));
        if (count.intValue() == 0) {
            return new PageResult<>();
        }
        List<AlbumBackDTO> photoAlbumDTOList = albumMapper.listPhotoAlbumDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<>(count.intValue(), photoAlbumDTOList);
    }

    /**
     * 更新或修改相册
     * @param photoAlbumVO   相册信息
     */
    @Override
    public void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO) {
        //查询像册名是否重复
        PhotoAlbumEntity albumEntity = albumMapper.selectOne(new LambdaQueryWrapper<PhotoAlbumEntity>()
                .select(PhotoAlbumEntity::getId)
                .eq(PhotoAlbumEntity::getAlbumName, photoAlbumVO.getAlbumName()));
        if (Objects.nonNull(albumEntity) && !albumEntity.getId().equals(photoAlbumVO.getId())) {
            //非空，且id一致
            throw new ServiceException("相册名已存在");
        }
        PhotoAlbumEntity albumEntity1 = BeanCopyUtils.copyObject(photoAlbumVO, PhotoAlbumEntity.class);
        this.saveOrUpdate(albumEntity1);
    }

    /**
     * 删除相册
     * 但是仅仅逻辑删除相册内的图片
     * @param albumId              相册id
     */
    @Override
    public void deletePhotoAlbumById(Integer albumId) {
        //相册内有多个图片
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<PhotoEntity>()
                .eq(PhotoEntity::getAlbumId, albumId));
        if (count > 0) {
            //存在多个图片，先逻辑删除图片和相册
            photoMapper.update(new PhotoEntity(), new LambdaUpdateWrapper<PhotoEntity>()
                    .set(PhotoEntity::getIsDelete, CommonConstants.TRUE)
                    .eq(PhotoEntity::getAlbumId, albumId));
            albumMapper.updateById(PhotoAlbumEntity.builder()
                    .id(albumId)
                    .isDelete(CommonConstants.TRUE)
                    .build());
        } else {
            //不存在，直接删除相册
            photoMapper.deleteById(albumId);
        }
    }

    /**
     * 根据AlbumId获取后台相册
     * @param albumId       相册Id
     * @return              后台相册
     */
    @Override
    public AlbumBackDTO getPhotoAlbumDTOById(Integer albumId) {
        PhotoAlbumEntity album = albumMapper.selectById(albumId);
        //查询相册内未被逻辑删除的图片数量
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<PhotoEntity>()
                .eq(PhotoEntity::getAlbumId, albumId)
                .eq(PhotoEntity::getIsDelete, CommonConstants.FALSE));

        AlbumBackDTO albumBackDTO = BeanCopyUtils.copyObject(album, AlbumBackDTO.class);
        albumBackDTO.setPhotoCount(count.intValue());
        return albumBackDTO;
    }
}
