package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评论
 *
 * @author senko
 * @date 2022/7/23 14:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "评论")
public class CommentVO {

    /**
     * 文章ID / Topic Id
     */
    @ApiModelProperty("评论主题ID")
    private Integer topicId;

    /**
     * 评论类型 1:文章的评论 2:友链的评论 3:说说的评论
     */
    @NotNull(message = "评论类型不能为空：1-文章评论 2-友链 3-说说")
    @ApiModelProperty(required = true, dataType = "Integer", value = "评论类型 1:文章的评论 2:友链的评论 3:说说的评论")
    private Integer type;

    /**
     * 评论人ID
     */
    @ApiModelProperty(required = true, dataType = "Integer", value = "评论人ID")
    private Integer replyUserId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(required = true, dataType = "String", value = "评论内容")
    private String commentContent;

    /**
     * 父评论ID
     */
    @ApiModelProperty(dataType = "Integer", value = "父评论ID")
    private Integer parentId;

}
