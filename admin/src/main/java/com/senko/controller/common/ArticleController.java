package com.senko.controller.common;

import com.senko.common.constants.FilePathConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.ArticleBackDTO;
import com.senko.common.core.vo.ArticleVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.framework.strategy.context.UploadStrategyContext;
import com.senko.system.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Api("文章模块")
@RestController
public class ArticleController {

    private IArticleService articleService;

    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    public ArticleController(IArticleService articleService, UploadStrategyContext uploadStrategyContext) {
        this.articleService = articleService;
        this.uploadStrategyContext = uploadStrategyContext;
    }

    /**
     * 查询文章后台（不带内容）
     * @param conditionVO
     * @return
     */
    @ApiOperation("查询后台文章")
    @GetMapping("/admin/articles")
    public AjaxResult<PageResult<ArticleBackDTO>> listArticleBacks(ConditionVO conditionVO) {
        return AjaxResult.success(articleService.listArticleBacks(conditionVO));
    }

    /**
     * 以文章id去查询文章（带内容）
     * @param articleId
     * @return
     */
    @ApiOperation("根据文章ID获取文章")
    @GetMapping("/admin/articles/{articleId}")
    public AjaxResult<ArticleVO> getArticleBackByArticleId(@PathVariable("articleId") Integer articleId) {
        return AjaxResult.success(articleService.getArticleBackByArticleId(articleId));
    }

    /**
     * 添加/修改文章
     */
    @ApiOperation("添加/修改文章")
    @PostMapping("/admin/articles")
    public AjaxResult saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVo) {
        articleService.saveOrUpdateArticle(articleVo);
        return AjaxResult.success();
    }

    @ApiOperation("上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/articles/images")
    public AjaxResult<String> saveArticleImages(MultipartFile file) {
        String filePath = uploadStrategyContext.executeUploadFile(file, FilePathConstants.ARTICLE);
        return AjaxResult.success("上传成功", filePath);
    }

}
