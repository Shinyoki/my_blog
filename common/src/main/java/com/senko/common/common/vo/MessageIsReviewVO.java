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
 * 留言审核状态VO
 *
 * @author senko
 * @date 2022/5/25 7:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("留言审核状态VO")
public class MessageIsReviewVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 留言ID 集合
     */
    @NotEmpty(message = "留言ID不能为空")
    @ApiModelProperty(value = "留言ID集合", required = true)
    private List<Integer> idList;

    /**
     * 希望改为的审核状态 0:审核中 1:正常
     */
    @NotNull(message = "审核状态不能为空")
    @ApiModelProperty(value = "希望改为的审核状态 0:审核中 1:正常", required = true)
    private Integer isReview;
}
