package com.senko.controller.common;

import com.senko.common.common.vo.WebsiteConfigVO;
import com.senko.common.constants.FilePathConstants;
import com.senko.common.core.AjaxResult;
import com.senko.framework.strategy.context.UploadStrategyContext;
import com.senko.system.service.IWebsiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

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

    /**
     * 上传网站头像
     * @param file          头像文件
     * @return              头像地址
     */
    @ApiOperation("上传网站头像")
    @ApiImplicitParam(name = "file", value = "头像文件", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/config/images")
    public AjaxResult<?> uploadWebsiteAvatar(MultipartFile file) {
        return AjaxResult.success("上传成功", uploadStrategyContext.executeUploadFile(file, FilePathConstants.CONFIG));
    }

    /**
     * 更新网站配置
     * @param configVO    网站配置
     */
    @ApiOperation("更新网站配置")
    @PutMapping("/admin/website/config")
    public AjaxResult<?> updateWebsiteConfig(@RequestBody WebsiteConfigVO configVO) {
        websiteConfigService.updateWebsiteConfig(configVO);
        return AjaxResult.success("更新成功");
    }
}
