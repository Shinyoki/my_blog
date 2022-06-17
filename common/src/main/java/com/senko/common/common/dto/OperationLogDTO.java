package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台操作日志DTO
 * @author senko
 * @date 2022/6/17 14:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台操作日志DTO")
public class OperationLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @ApiModelProperty("日志ID")
    private Integer id;

    /**
     * 操作模块
     */
    @ApiModelProperty("操作模块")
    private String optModule;

    /**
     * 操作地址
     */
    @ApiModelProperty("操作地址")
    private String optUrl;

    /**
     * 操作类型
     */
    @ApiModelProperty("操作类型")
    private String optType;

    /**
     * 操作方法
     */
    @ApiModelProperty("操作方法")
    private String optMethod;

    /**
     * 请求方法
     */
    @ApiModelProperty("请求方法")
    private String requestMethod;

    /**
     * 操作描述
     */
    @ApiModelProperty("操作描述")
    private String optDesc;

    /**
     * 请求参数
     */
    @ApiModelProperty("请求参数")
    private String requestParam;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private String responseData;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户登录ip
     */
    @ApiModelProperty("用户登录ip")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty("ip来源")
    private String ipSource;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private LocalDateTime createTime;

}
