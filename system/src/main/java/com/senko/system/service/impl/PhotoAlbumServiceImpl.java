package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.PhotoAlbumMapper;
import com.senko.common.core.entity.PhotoAlbumEntity;
import com.senko.system.service.IPhotoAlbumService;


@Service("photoAlbumService")
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbumEntity> implements IPhotoAlbumService {

}