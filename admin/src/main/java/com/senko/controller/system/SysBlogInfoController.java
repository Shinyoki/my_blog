package com.senko.controller.system;

import com.senko.common.core.AjaxResult;
import com.senko.common.common.dto.BlogCountsInfoDTO;
import com.senko.common.common.dto.UserAreaDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ISysBlogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客相关信息查询接口
 *
 * @author senko
 * @date 2022/5/9 7:54
 */
@RestController
@Api(tags = "博客后台相关接口")
public class SysBlogInfoController {

    @Autowired
    private ISysBlogInfoService blogInfoService;

    /**
     * 查询后台信息
     */
    @ApiOperation("获取阅读量、文章量、标签分类等一系列后台信息")
    @GetMapping("/admin")
    public AjaxResult<BlogCountsInfoDTO> getBasicAdminInfo() {
        return AjaxResult.success(blogInfoService.getBasicAdminInfo());
    }

    /**
     * 记录当前的用户
     */
    @ApiOperation("记录来访用户的信息")
    @PostMapping("/record")
    public void record() {
        blogInfoService.recordCurView();
    }

    /**
     * 获取当前用户的地区信息
     * @param condition 条件
     * @return          地区信息
     */
    @ApiOperation("查询所有用户相关区域分布")
    @GetMapping("/admin/users/area")
    public AjaxResult<List<UserAreaDTO>> listOfUserAreas(ConditionVO condition) {
        return AjaxResult.success(blogInfoService.listOfUserAreas(condition));
    }

    /**
     * 获取我的描述
     * @return      描述
     */
    @ApiOperation("获取关于我的信息")
    @GetMapping("/about")
    public AjaxResult<String> getAboutInfo() {
        return AjaxResult.success("获取成功", blogInfoService.getAboutInfo());
    }
}
