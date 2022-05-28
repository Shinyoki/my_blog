package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 评论审核状态 VO
 *
 * @author senko
 * @date 2022/5/24 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("评论审核状态VO")
public class CommentIsReviewVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID 集合
     */
    @NotEmpty(message = "id集合不能为空")
    @ApiModelProperty(value = "评论id 集合", required = true)
    private List<Integer> idList;

    /**
     * 需要改成的审核状态 0：未审核 1：审核通过
     */
    @NotNull(message = "审核状态不能为空")
    @ApiModelProperty(value = "需要改成的审核状态 0：未审核 1：审核通过", required = true)
    private Integer isReview;

}
