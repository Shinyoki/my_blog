package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.senko.common.common.vo.WebsiteConfigVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.redis.RedisHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.WebsiteConfigMapper;
import com.senko.common.common.entity.WebsiteConfigEntity;
import com.senko.system.service.IWebsiteConfigService;

import java.util.Objects;


/**
 * 网站配置Service
 */
@Service("websiteConfigService")
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfigEntity> implements IWebsiteConfigService {
    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;

    @Autowired
    private RedisHandler redisHandler;

    /**
     * 获取网站配置
     * @return                网站配置
     */
    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO resultConfig = null;
        Object cacheConfig = redisHandler.get(RedisConstants.WEBSITE_CONFIG);
        if (Objects.nonNull(cacheConfig)) {
            //缓存存在
            resultConfig = JSON.parseObject(cacheConfig.toString(), WebsiteConfigVO.class);
        } else {
            //缓存失效
            WebsiteConfigEntity websiteConfigEntity = websiteConfigMapper.selectById(CommonConstants.DEFAULT_CONFIG_ID);
            //从持久层获取
            resultConfig = BeanCopyUtils.copyObject(websiteConfigEntity, WebsiteConfigVO.class);
            //更新缓存
            redisHandler.set(RedisConstants.WEBSITE_CONFIG, resultConfig);
        }
        return resultConfig;
    }
}
