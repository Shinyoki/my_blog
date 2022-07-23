package com.senko.common.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论回复
 *
 * @author senko
 * @date 2022/7/23 15:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCountDTO {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复数量
     */
    private Integer replyCount;

}
