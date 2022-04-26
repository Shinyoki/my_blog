package com.senko.controller;

import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.StatusCodeConstants;
import com.senko.common.core.AjaxResult;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 异常处理器
 * 受理非Controller抛出的异常
 * 对/error进行处理，如果有错误，会被重定向到 /error ，然后交给 BasicErrorController 处理
 *
 * @author senko
 * @date 2022/4/26 14:30
 */
@RestController
public class ErrorController extends BasicErrorController {

    /**
     * 传入初始参数
     */
    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * 返回数据
     *
     * 本纸就是个Controller，要么返回ResponseEntity、要么返回Model&View，
     * 所以是@RestController。
     * 以及对error()函数加上@RequestMapping注解，用于produces特定的响应。
     * @param request
     * @return
     */
    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        AjaxResult<Object> result = AjaxResult.error(StatusCodeConstants.FAIL.getCode(), "资源不存在404");
        return new ResponseEntity(result, HttpStatus.NOT_FOUND);
    }

    /**
     * 返回Model&View
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping(produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("/404", HttpStatus.NOT_FOUND);
    }
}
