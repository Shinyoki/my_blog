package com.senko.controller.common;

import com.senko.common.common.vo.WebsiteConfigVO;
import com.senko.common.core.AjaxResult;
import com.senko.system.service.IWebsiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 网站配置模块
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api(tags = "网站配置模块")
@RestController
public class WebsiteConfigController {

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    /**
     * 获取网站配置
     * @return                网站配置
     */
    @ApiOperation("获取网站配置")
    @GetMapping("/admin/website/config")
    public AjaxResult<WebsiteConfigVO> getWebsiteConfig() {
        WebsiteConfigVO config = websiteConfigService.getWebsiteConfig();
        return AjaxResult.success(config);
    }
}
