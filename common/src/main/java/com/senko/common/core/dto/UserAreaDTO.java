package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户地区DTO
 *
 * 用户所在地区和数量
 * @author senko
 * @date 2022/5/13 20:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户所在地区 名
     */
    private String name;

    /**
     * 数量
     */
    private Long value;
}
