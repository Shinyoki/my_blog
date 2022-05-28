package com.senko.common.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 文章数
 * 记录某一天时的文章总量
 *
 * @author senko
 * @date 2022/5/6 21:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ArticlesOnOneDayDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 某一天
     *
     * String类型
     */
    private String date;

    /**
     * 当天文章数量
     */
    private Integer count;

}
