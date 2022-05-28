package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.WebsiteConfigMapper;
import com.senko.common.common.entity.WebsiteConfigEntity;
import com.senko.system.service.IWebsiteConfigService;


@Service("websiteConfigService")
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfigEntity> implements IWebsiteConfigService {

}
