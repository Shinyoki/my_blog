package com.senko.common.core.dto;

import java.io.Serializable;

/**
 * 单日访问量
 *
 * @author senko
 * @date 2022/5/6 21:12
 */
public class UniqueViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 某日时间戳
     *
     * String类型
     */
    private String day;

    /**
     * 访问量
     */
    private Integer viewsCount;

}
