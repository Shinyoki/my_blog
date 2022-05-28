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
 * 后台评论DTO
 * @author senko
 * @date 2022/5/24 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("后台评论DTO")
public class CommentBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @ApiModelProperty("评论ID")
    private Integer id;

    /**
     * 评论类型
     */
    @ApiModelProperty("评论类型")
    private String type;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String commentContent;

    /**
     * 发表时间
     */
    @ApiModelProperty("是否审核过了")
    private Integer isReview;

    /**
     * 发表时间
     */
    @ApiModelProperty("发表时间")
    private LocalDateTime createTime;

    // =================字段转换为信息================

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 被回复用户昵称
     */
    @ApiModelProperty("被回复用户昵称")
    private String replyNickname;

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    private String articleTitle;

}
