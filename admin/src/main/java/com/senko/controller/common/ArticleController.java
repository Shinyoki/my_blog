package com.senko.controller.common;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.vo.ArticleVO;
import com.senko.system.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 文章
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Api("文章模块")
@RestController
@RequestMapping("core/article")
public class ArticleController {

    private IArticleService articleService;

    @Autowired
    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 添加/修改文章
     */
    @ApiOperation("添加/修改文章")
    @PostMapping("/admin/articles")
    public AjaxResult saveOrUpdateArticle(@Valid @RequestBody ArticleVO article) {

        return AjaxResult.success();
    }
}
