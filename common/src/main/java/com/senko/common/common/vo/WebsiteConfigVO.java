package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 网站配置VO兼DTO
 * @author senko
 * @date 2022/6/18 19:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "网站配置")
public class WebsiteConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 网站头像
     */
    @ApiModelProperty(value = "网站头像",required = true)
    private String websiteAvatar;

    /**
     * 网站名称
     */
    @ApiModelProperty(value = "网站名称",required = true)
    private String websiteName;

    /**
     * 网站作者
     */
    @ApiModelProperty(value = "网站作者",required = true)
    private String websiteAuthor;

    /**
     * 网站简介
     */
    @ApiModelProperty(value = "网站简介",required = true)
    private String websiteIntro;

    /**
     * 网站公告
     */
    @ApiModelProperty(value = "网站公告",required = true)
    private String websiteNotice;

    /**
     * 网站创建时间
     */
    @ApiModelProperty(value = "网站创建时间",required = true)
    private String websiteCreateTime;

    /**
     * 网站备案号
     */
    @ApiModelProperty(value = "网站备案号",required = true)
    private String websiteRecordNo;

    /**
     * 社交登录列表
     */
    @ApiModelProperty(value = "社交登录列表",required = true)
    private String socialLoginList;

    /**
     * 社交Url列表
     */
    @ApiModelProperty(value = "社交Url列表",required = true)
    private String socialUrlList;

    /**
     * qq号
     */
    @ApiModelProperty(value = "qq号",required = true)
    private String qq;

    /**
     * github地址
     */
    @ApiModelProperty(value = "github地址",required = true)
    private String github;

    /**
     * 码云地址
     */
    @ApiModelProperty(value = "码云地址",required = true)
    private String gitee;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像",required = true)
    private String userAvatar;

    /**
     * 游客头像
     */
    @ApiModelProperty(value = "游客头像",required = true)
    private String visitorAvatar;

    /**
     * 开启评论审核 0-关闭 1-开启
     */
    @ApiModelProperty(value = "开启评论审核 0-关闭 1-开启",required = true)
    private Integer commentReview;

    /**
     * 开启留言审核 0-关闭 1-开启
     */
    @ApiModelProperty(value = "开启留言审核 0-关闭 1-开启",required = true)
    private Integer messageReview;

    /**
     * 开启邮箱通知 0-关闭 1-开启
     */
    @ApiModelProperty(value = "开启邮箱通知 0-关闭 1-开启",required = true)
    private Integer emailNotice;

    /**
     * 开启接受赞助
     */
    @ApiModelProperty(value = "开启接受赞助",required = true)
    private Integer isReward;

    /**
     * 微信二维码
     */
    @ApiModelProperty(value = "微信二维码",required = true)
    private String weixinQRCode;

    /**
     * 支付宝二维码
     */
    @ApiModelProperty(value = "支付宝二维码",required = true)
    private String alipayQRCode;

    /**
     * 开启聊天室 0-关闭 1-开启
     */
    @ApiModelProperty(value = "开启聊天室 0-关闭 1-开启",required = true)
    private Integer isChatRoom;

    /**
     * 聊天室地址
     */
    @ApiModelProperty(value = "聊天室地址",required = true)
    private String websocketUrl;

    /**
     * 开启音乐组件
     */
    @ApiModelProperty(value = "开启音乐组件",required = true)
    private Integer isMusicPlayer;
}
