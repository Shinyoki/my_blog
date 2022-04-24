package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ResourceMapper;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.system.service.IResourceService;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements IResourceService {

}