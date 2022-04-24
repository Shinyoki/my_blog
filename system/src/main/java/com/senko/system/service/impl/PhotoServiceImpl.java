package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.PhotoMapper;
import com.senko.common.core.entity.PhotoEntity;
import com.senko.system.service.IPhotoService;


@Service("photoService")
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, PhotoEntity> implements IPhotoService {

}