package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.AlbumBackDTO;
import com.senko.common.common.vo.PhotoAlbumVO;
import com.senko.common.constants.FilePathConstants;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.framework.strategy.context.UploadStrategyContext;
import com.senko.system.service.IPhotoAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 相册Controller
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api("相册模块")
@RestController
public class PhotoAlbumController {
    @Autowired
    private IPhotoAlbumService photoAlbumService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 获取后台相册 分页集合
     * @param conditionVO       查询条件
     * @return                  后台相册 分页结果
     */
    @ApiOperation("获取后台相册分页集合")
    @GetMapping("/admin/photos/albums")
    public AjaxResult<PageResult<AlbumBackDTO>> listPhotoAlbumDTO(ConditionVO conditionVO) {
        PageResult<AlbumBackDTO> pageResult = photoAlbumService.listPhotoAlbumDTO(conditionVO);
        return AjaxResult.success(pageResult);
    }

    /**
     * 根据AlbumId获取后台相册
     * @param albumId       相册Id
     * @return              后台相册
     */
    @ApiOperation("查询后台相册信息")
    @GetMapping("/admin/photos/albums/{albumId}/info")
    public AjaxResult<AlbumBackDTO> getPhotoAlbumDTOById(@PathVariable("albumId") Integer albumId) {
        return AjaxResult.success("查询成功", photoAlbumService.getPhotoAlbumDTOById(albumId));
    }

    /**
     * 上传相册图片
     * @param file              图片文件
     * @return                  封面路径
     */
    @ApiOperation("上传相册封面")
    @ApiImplicitParam(name = "file", value = "相册封面", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/photos/albums/cover")
    public AjaxResult<?> saveAlbumCover(MultipartFile file) {
        String urlPath = uploadStrategyContext.executeUploadFile(file, FilePathConstants.PHOTO);
        return AjaxResult.success("封面上传成功",urlPath);
    }

    /**
     * 更新或修改相册
     * @param photoAlbumVO   相册信息
     */
    @LogOperation(optType = OperationTypeConstants.SAVE_OR_UPDATE)
    @ApiOperation("保存或更新相册")
    @PostMapping("/admin/photos/albums")
    public AjaxResult<?> saveOrUpdatePhotoAlbum(@Valid @RequestBody PhotoAlbumVO photoAlbumVO) {
        photoAlbumService.saveOrUpdatePhotoAlbum(photoAlbumVO);
        return AjaxResult.success("保存或更新成功");
    }

    /**
     * 删除相册
     * @param id              相册id
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除相册")
    @DeleteMapping("/admin/photos/albums/{id}")
    public AjaxResult<?> deletePhotoAlbum(@NotNull @PathVariable("id") Integer id) {
        photoAlbumService.deletePhotoAlbumById(id);
        return AjaxResult.success("删除成功");
    }
}
