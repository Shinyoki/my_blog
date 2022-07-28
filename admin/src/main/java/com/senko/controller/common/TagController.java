package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.TagBackDTO;
import com.senko.common.common.dto.TagCountDTO;
import com.senko.common.common.dto.TagDTO;
import com.senko.common.common.vo.TagVO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 标签
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api(tags = "标签模块")
@RestController
public class TagController {

    private ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 搜索文章标签
     *
     * @param conditionVO 条件
     * @return 标签 集合
     */
    @ApiOperation("搜索文章标签")
    @GetMapping("/admin/tags/search")
    public AjaxResult<List<TagDTO>> listTagsBySearch(ConditionVO conditionVO) {
        return AjaxResult.success(tagService.listTagsBySearch(conditionVO));
    }

    /**
     * 搜索文章标签
     *
     * @param conditionVO 条件
     * @return 标签（文章量、创建时间） 分页集合
     */
    @ApiOperation("查询后台标签")
    @GetMapping("/admin/tags")
    public AjaxResult<PageResult<TagBackDTO>> listTagBack(ConditionVO conditionVO) {
        PageResult<TagBackDTO> tagBackList = tagService.listTagBack(conditionVO);
        return AjaxResult.success(tagBackList);
    }

    /**
     * 添加或修改标签
     *
     * @param tagVO 标签（标签名不能为空）
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("添加或修改标签")
    @PostMapping("/admin/tags")
    public AjaxResult<?> saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return AjaxResult.success();
    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id 集合
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除标签")
    @DeleteMapping("/admin/tags")
    public AjaxResult<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return AjaxResult.success();
    }

    @ApiOperation("获取标签")
    // 处理application/json请求
    @GetMapping( value = "/tags")
    public AjaxResult<PageResult<TagCountDTO>> listTagCount() {
        PageResult<TagCountDTO> tagCountDTOPageResult = tagService.listTagCount();
        return AjaxResult.success(tagCountDTOPageResult);
    }

}
