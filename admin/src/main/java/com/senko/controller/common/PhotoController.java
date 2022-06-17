package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.PhotoBackDTO;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.common.vo.PhotoInfoVO;
import com.senko.common.common.vo.PhotoVO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IPhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 照片Controller
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api(tags = "照片模块")
@RestController
public class PhotoController {
    @Autowired
    private IPhotoService photoService;

    /**
     * 获取后台照片 分页集合
     * @param conditionVO    查询条件
     * @return               后台照片 分页结果
     */
    @ApiOperation("获取照片分页集合")
    @GetMapping("/admin/photos")
    public AjaxResult<PageResult<PhotoBackDTO>> listPhotoBackDTOList(ConditionVO conditionVO) {
        PageResult<PhotoBackDTO> pageResult = photoService.listPhotoBackDTOList(conditionVO);
        return AjaxResult.success(pageResult);
    }

    /**
     * 上传图片到对应相册
     * @param photoVO   图片集合、相册ID
     */
    @LogOperation(optType = OperationTypeConstants.SAVE)
    @ApiOperation("保存照片")
    @PostMapping("/admin/photos")
    public AjaxResult<?> uploadPhotos(@Valid @RequestBody PhotoVO photoVO) {
        photoService.uploadPhotos(photoVO);
        return AjaxResult.success();
    }

    /**
     * 更新图片信息
     * @param photoInfoVO 图片信息
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("更新照片信息")
    @PutMapping("/admin/photos")
    public AjaxResult<?> updatePhoto(@Valid @RequestBody PhotoInfoVO photoInfoVO) {
        photoService.updatePhoto(photoInfoVO);
        return AjaxResult.success();
    }

    /**
     * 逻辑删除图片
     * @param deleteVO 图片IDS，删除状态码 0:未删除 1:已删除
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("逻辑删除照片")
    @PutMapping("/admin/photos/delete")
    public AjaxResult<?> logicDeletePhoto(@Valid @RequestBody DeleteVO deleteVO) {
        photoService.logicDeletePhoto(deleteVO);
        return AjaxResult.success();
    }

    /**
     * 批量删除图片
     * @param idList    图片IDS
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("物理删除照片")
    @DeleteMapping("/admin/photos")
    public AjaxResult<?> deletePhotos(@NotEmpty @RequestBody List<Integer> idList) {
        photoService.removeBatchByIds(idList);
        return AjaxResult.success();
    }
    /**
     * 移动图片到指定相册
     * @param photoVO 图片IDS、相册ID
     */
    @LogOperation(optType = OperationTypeConstants.UPDATE)
    @ApiOperation("移动图片到其他相册")
    @PutMapping("/admin/photos/album")
    public AjaxResult<?> movePhotoToAlbum(@Valid @RequestBody PhotoVO photoVO) {
        photoService.movePhotoToAlbum(photoVO);
        return AjaxResult.success();
    }
}
