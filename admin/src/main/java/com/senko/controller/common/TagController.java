package com.senko.controller.common;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.dto.TagDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 标签
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("标签模块")
@RestController
public class TagController {

    private ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation("搜索文章标签")
    @GetMapping("/admin/tags/search")
    public AjaxResult<List<TagDTO>> listTagsBySearch(ConditionVO conditionVO) {
        return AjaxResult.success(tagService.listTagsBySearch(conditionVO));
    }
}
