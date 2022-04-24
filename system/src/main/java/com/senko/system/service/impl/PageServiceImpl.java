package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.PageMapper;
import com.senko.common.core.entity.PageEntity;
import com.senko.system.service.IPageService;


@Service("pageService")
public class PageServiceImpl extends ServiceImpl<PageMapper, PageEntity> implements IPageService {

}