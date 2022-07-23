package com.senko.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论DTO
 *
 * @author senko
 * @date 2022/7/23 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("评论DTO")
public class CommentDTO {

    /**
     * 评论ID
     */
    @ApiModelProperty("评论ID")
    private Integer id;

    /**
     * 评论用户ID
     */
    @ApiModelProperty("评论用户ID")
    private Integer userId;

    /**
     * 评论用户昵称
     */
    @ApiModelProperty("评论用户昵称")
    private String nickname;

    /**
     * 评论用户头像
     */
    @ApiModelProperty("评论用户头像")
    private String avatar;

    /**
     * 评论用户个人网站
     */
    @ApiModelProperty("评论用户个人网站")
    private String webSite;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String commentContent;

    /**
     * 评论点赞数
     */
    @ApiModelProperty("评论点赞数")
    private Integer likeCount;

    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

    /**
     * 回复量
     */
    @ApiModelProperty("回复量")
    private Integer replyCount;


    /**
     * 子回复列表
     */
    @ApiModelProperty("子回复列表")
    private List<ReplyDTO> replyDTOList;

}
