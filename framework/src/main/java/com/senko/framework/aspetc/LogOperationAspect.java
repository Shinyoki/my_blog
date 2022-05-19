package com.senko.framework.aspetc;

import com.alibaba.fastjson.JSON;
import com.senko.common.annotation.LogOperation;
import com.senko.common.core.entity.OperationLogEntity;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.system.mapper.OperationLogMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * 操作日志记录 切面
 * @author senko
 * @date 2022/5/18 11:59
 */
@Component
@Aspect
public class LogOperationAspect {

    @Autowired
    private OperationLogMapper logMapper;

    /**
     * 日志记录切面
     *
     * https://blog.csdn.net/xixiyuguang/article/details/120826268
     * 切面：{@link LogOperation}注解标注的方法
     * 依赖Swagger的{@link Api}等函数注解来获取更多信息
     * @param joinPoint     织入点
     * @param logOperation  注解
     * @param returnVal     被增强函数返回值
     */
    @AfterReturning(value = "@annotation(logOperation)", returning = "returnVal")
    public void saveOptLog(JoinPoint joinPoint, LogOperation logOperation, Object returnVal) {
        //请求属性
        HttpServletRequest request = ServletUtils.getRequest();

        //织入点是方法，因此获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        //获取Swagger API注解
        //获取目标方法的所属类
        Api apiAnno = ((Api) methodSignature.getDeclaringType().getAnnotation(Api.class));
        ApiOperation apiOperationAnno = method.getAnnotation(ApiOperation.class);


        OperationLogEntity operationLogEntity = OperationLogEntity.builder()
                //所属Api模块
                .optModule(Optional.ofNullable(apiAnno).map(api -> api.value()).orElse("未知模块"))
                //操作描述
                .optDesc(Optional.ofNullable(apiOperationAnno).map(apiOperation -> apiOperation.value()).orElse("未知操作"))
                //操作类型
                .optType(logOperation.optType())
                //请求方法
                .requestMethod(request.getMethod())
                //当前函数
                .optMethod(methodSignature.getDeclaringTypeName() + methodSignature.getName())
                //请求参数
                .requestParam(JSON.toJSONString(joinPoint.getArgs()))
                //返回结果
                .responseData(JSON.toJSONString(returnVal))
                //用户ID
                .userId(Optional.ofNullable(SecurityUtils.getLoginUser()).map(userDetailsDTO -> userDetailsDTO.getUserInfoId()).orElse(null))
                //昵称
                .nickname(SecurityUtils.getLoginUser().getNickname())
                //请求IP
                .ipAddress(IpUtils.getIpAddressFromRequest(request))
                .ipSource(IpUtils.getIpSource(request))
                .optUrl(request.getRequestURI())
                .build();

        logMapper.insert(operationLogEntity);
    }
}
