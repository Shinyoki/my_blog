package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 回复DTO
 *
 * @author senko
 * @date 2022/7/23 14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("回复DTO")
public class ReplyDTO {

    /**
     * 父评论id
     */
    @ApiModelProperty("父评论id")
    private Integer parentId;

    /**
     * 父亲评论用户id
     */
    @ApiModelProperty("父亲评论用户id")
    private Integer replyUserId;

    /**
     * 父评论用户昵称
     */
    @ApiModelProperty("父评论用户昵称")
    private String replyNickname;

    /**
     * 网页
     */
    @ApiModelProperty("网页")
    private String replyWebSite;

    /**
     * 评论ID
     */
    @ApiModelProperty("评论ID")
    private Integer id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 个人网站
     */
    @ApiModelProperty("个人网站")
    private String webSite;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String commentContent;

    /**
     * 点赞量
      */
    @ApiModelProperty("点赞量")
    private Integer likeCount;

    /**
     * 回复时间
     */
    @ApiModelProperty("回复时间")
    private LocalDateTime createTime;

}
