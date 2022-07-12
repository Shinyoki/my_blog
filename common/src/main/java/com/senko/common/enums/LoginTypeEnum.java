package com.senko.common.enums;

/**
 * 登录类型
 * @author senko
 * @date 2022/7/12 6:37
 */
public enum LoginTypeEnum {
    EMAIL(1, "邮箱登录", ""),
    QQ(2, "QQ登录", "qqLoginStrategyImpl"),
    GITHUB(3, "GITHUB登录", "githubLoginStrategyImpl");

    private Integer type;       // 登录类型
    private String desc;        // 描述
    private String strategy;    // 策略名

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getStrategy() {
        return strategy;
    }

    LoginTypeEnum(Integer type, String desc, String strategy) {
        this.type = type;
        this.desc = desc;
        this.strategy = strategy;
    }
}
