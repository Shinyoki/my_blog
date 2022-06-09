package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.common.vo.TalkVO;
import com.senko.common.constants.FilePathConstants;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.framework.strategy.context.UploadStrategyContext;
import com.senko.system.service.ITalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 说说Controller
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Api("说说Controller")
@RestController
public class TalkController {
    @Autowired
    private ITalkService talkService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 查询后台说说  分页集合
     * @param conditionVO   条件
     * @return              后台说说 分页集合
     */
    @ApiOperation("获取后台说说 分页集合")
    @GetMapping("/admin/talks")
    public AjaxResult<PageResult<TalkBackDTO>> listTalkBack(ConditionVO conditionVO) {
        PageResult<TalkBackDTO> talkBackDTOPageResult = talkService.listTalkBack(conditionVO);
        return AjaxResult.success(talkBackDTOPageResult);
    }

    /**
     * 根据talkId查询后台说说
     * @param talkId        talkId
     * @return              后台说说
     */
    @ApiOperation("根据TalkId查询后台说说")
    @GetMapping("/admin/talks/{talkId}")
    public AjaxResult<TalkBackDTO> getTalkBackById(@NotNull @PathVariable("talkId") Integer talkId) {
        TalkBackDTO talkBackDTO = talkService.getTalkBackById(talkId);
        return AjaxResult.success(talkBackDTO);
    }

    /**
     * 删除说说 集合
     * @param talkIdList        说说ID 集合
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除说说 集合")
    @DeleteMapping("/admin/talks")
    public AjaxResult<?> deleteTalks(@RequestBody List<Integer> talkIdList) {
        talkService.removeBatchByIds(talkIdList);
        return AjaxResult.success();
    }

    /**
     * 上传说说图片
     * @param file      图片数据
     * @return          图片链接
     */
    @ApiOperation("上传说说图片")
    @ApiImplicitParam(name = "file", value = "说说图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/talks/images")
    public AjaxResult<String> uploadTalkImage(MultipartFile file) {
        String path = uploadStrategyContext.executeUploadFile(file, FilePathConstants.TALKS);
        return AjaxResult.success("上传成功", path);
    }

    /**
     * 新增后台说说
     * @param talkVO    说说id、说说内容、说说图片、说说状态、置顶状态
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("保存或修改说说")
    @PostMapping("/admin/talks")
    public AjaxResult<?> saveOrUpdateTalk(@Valid @RequestBody TalkVO talkVO) {
        talkService.saveOrUpdateTalk(talkVO);
        return AjaxResult.success();
    }
}
