package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.TagMapper;
import com.senko.common.core.entity.TagEntity;
import com.senko.system.service.ITagService;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements ITagService {

}