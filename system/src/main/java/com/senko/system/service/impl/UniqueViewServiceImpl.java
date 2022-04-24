package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UniqueViewMapper;
import com.senko.common.core.entity.UniqueViewEntity;
import com.senko.system.service.IUniqueViewService;


@Service("uniqueViewService")
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueViewEntity> implements IUniqueViewService {

}