package com.senko.common.core.dto;

import java.io.Serializable;

/**
 * 标签 DTO
 *
 * @author senko
 * @date 2022/5/6 19:58
 */
public class TagDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

}
