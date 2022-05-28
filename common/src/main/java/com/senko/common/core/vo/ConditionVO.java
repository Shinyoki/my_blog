package com.senko.common.core.vo;

import com.senko.common.common.entity.MessageEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 查询条件View Object
 *
 * @author senko
 * @date 2022/5/3 9:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询条件")
public class ConditionVO {
    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Long current;

    /**
     * 页数
     */
    @ApiModelProperty("页数")
    private Long size;

    /**
     * 搜索关键词
     */
    @ApiModelProperty("搜索关键词")
    private String keywords;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long categoryId;

    /**
     * 标签id
     */
    @ApiModelProperty("标签id")
    private Long tagId;

    /**
     * 相册id
     */
    @ApiModelProperty("相册id")
    private Long albumId;

    /**
     * 登录类型
     */
    @ApiModelProperty("登录类型")
    private String loginType;

    /**
     * 用户类型：1-普通用户，2-annoymous游客
     */
    @ApiModelProperty("用户类型：1-普通用户，2-annoymous游客")
    private Integer type;

    /**
     * 说说状态：1-公开, 2-私密
     */
    @ApiModelProperty("说说状态：1-公开, 2-私密")
    private Integer status;

    /**
     * 是否被删除: 0-未删除，1-已删除
     *
     * MybatisPlus的逻辑删除只适用于他们自己继承实现的方法，
     * 开发者自定义查询时要手动根据逻辑删除字段进行判断
     */
    @ApiModelProperty("是否被删除: 0-未删除，1-已删除")
    private Integer isDelete;

    /**
     * 是否审核过该Message：0-未审核，1-已审核
     * {@link MessageEntity#isReview}
     */
    @ApiModelProperty("是否审核过该Message：0-未审核，1-已审核")
    private Integer isReview;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

}
