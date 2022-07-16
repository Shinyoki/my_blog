package com.senko.controller.common;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.ArticleBackDTO;
import com.senko.common.common.dto.ArticleHomeDTO;
import com.senko.common.common.vo.ArticleTopVO;
import com.senko.common.common.vo.ArticleVO;
import com.senko.common.common.vo.DeleteVO;
import com.senko.common.constants.FilePathConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
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

import static com.senko.common.constants.OperationTypeConstants.*;

/**
 * 文章
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Api(tags = "文章模块")
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
     * 查询首页的文章
     * @param conditionVO   查询条件
     * @return              首页文章集合
     */
    @ApiOperation("查询首页的文章")
    @GetMapping("/articles")
    public AjaxResult<List<ArticleHomeDTO>> listHomeArticles(ConditionVO conditionVO) {
        List<ArticleHomeDTO> articleHomeDTOList = articleService.listHomeArticles(conditionVO);
        return AjaxResult.success(articleHomeDTOList);
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
    @LogOperation(optType = SAVE_OR_UPDATE)
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

    @LogOperation(optType = UPDATE)
    @ApiOperation("修改文章置顶状态")
    @PutMapping("/admin/articles/top")
    public AjaxResult updateArticleTop(@Valid @RequestBody ArticleTopVO articleTopVO) {
        articleService.updateArticleTop(articleTopVO);
        return AjaxResult.success();
    }

    @LogOperation(optType = UPDATE)
    @ApiOperation("恢复/删除文章")
    @PutMapping("/admin/articles")
    public AjaxResult updateArticleDelete(@Valid @RequestBody DeleteVO deleteVO) {
        articleService.updateArticleDelete(deleteVO);
        return AjaxResult.success();
    }

    /**
     * 完全删除文章id集合对应的数据
     * @param articleIdList 文章id 集合
     */
    @LogOperation(optType = DELETE)
    @ApiOperation("完全删除文章")
    @DeleteMapping("/admin/articles")
    public AjaxResult deleteArticles(@RequestBody List<Integer> articleIdList) {
        articleService.deleteArticles(articleIdList);
        return AjaxResult.success();
    }
}
