package com.senko.framework.web.exception;


import javax.servlet.http.HttpServletRequest;

import com.senko.common.constants.StatusCodeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.exceptions.user.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Controller层的全局异常受理器
 * 只能处理由Controller层抛出的异常
 *
 * @author senko
 * @date 2022/4/26 12:02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常
     *
     * @param e       Exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handlerAll(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        LOGGER.error("请求地址：\"{}\"，发生异常:\"{}\"", requestURI, e.getMessage());
        e.printStackTrace();
        return AjaxResult.error(StatusCodeConstants.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 用户异常
     *
     * @param e       UserException
     * @param request
     * @return
     */
    @ExceptionHandler(UserException.class)
    public AjaxResult userExceptionHandler(UserException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        LOGGER.error("请求地址：\"{}\"，发生异常:\"\n{}\"", requestURI, e.getMessage());
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult validationExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败：");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb
                    .append(fieldError.getField())
                    .append("：")
                    .append(fieldError.getDefaultMessage());
        }
        LOGGER.error("请求地址：\"{}\"，发生异常:\"\n{}\"", requestURI,sb.toString());
        return AjaxResult.error(StatusCodeConstants.VALID_ERROR.getCode(), Objects.requireNonNull(sb.toString()));
    }
}
