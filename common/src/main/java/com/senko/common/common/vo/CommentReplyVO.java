package com.senko.common.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 评论回复VO
 *
 * @author senko
 * @date 2022/7/24 9:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评论回复VO")
public class CommentReplyVO {

    /**
     * current
     */
    @NotNull(message = "当前页不能为空")
    private Integer current;

    /**
     * size
     */
    @NotNull(message = "总页数不能为空")
    private Integer size;

}
