package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.entity.WebsiteConfigEntity;
import com.senko.common.common.vo.WebsiteConfigVO;

/**
 * 网站配置Service
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface IWebsiteConfigService extends IService<WebsiteConfigEntity> {


    /**
     * 获取网站配置
     * @return                网站配置
     */
    WebsiteConfigVO getWebsiteConfig();

    /**
     * 更新网站配置
     * @param configVO    网站配置
     */
    void updateWebsiteConfig(WebsiteConfigVO configVO);
}

