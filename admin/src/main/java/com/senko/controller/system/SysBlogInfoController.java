package com.senko.controller.system;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.BlogCountsInfoDTO;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.uuid.UUID;
import com.senko.system.service.ISysBlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客相关信息查询接口
 *
 * @author senko
 * @date 2022/5/9 7:54
 */
@RestController
public class SysBlogInfoController {

    @Autowired
    private ISysBlogInfoService blogInfoService;

    /**
     * 查询后台信息
     *
     * @return
     */
    @GetMapping("/admin")
    public AjaxResult<BlogCountsInfoDTO> getBasicAdminInfo() {
        return AjaxResult.success(blogInfoService.getBasicAdminInfo());
    }

    /**
     * 记录当前的用户
     */
    @PostMapping("/record")
    public void record() {
        blogInfoService.recordCurView();
    }

}
