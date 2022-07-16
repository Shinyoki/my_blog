package com.senko.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 区分不同用户
 *
 * @author senko
 * @date 2022/5/13 21:15
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    /**
     * 登录用户
     */
    USER(2, "用户"),
    /**
     * 游客
     */
    VISITOR(3, "游客");

    private final Integer roleId;

    private final String desc;

    /**
     * 获取相应的Enum
     *
     * @param type 用户类型
     */
    public static UserTypeEnum getUserByType(Integer type) {
        for (UserTypeEnum value : values()) {
            if (value.getRoleId().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
