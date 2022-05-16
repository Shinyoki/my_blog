package com.senko.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传模式枚举
 * 与 {@link AbstractUploadStrategyImpl} 的@Service名绑定
 * @author senko
 * @date 2022/5/16 21:14
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {
    /**
     * local 本地存储
     */
    LOCAL("local", "localUploadStrategyImpl"),

    /**
     * Oss 对象存储
     */
    OSS("oss", "ossUploadStrategyImpl");

    /**
     * 传输模式
     */
    private String mode;

    /**
     * 策略Impl
     */
    private String strategy;


    /**
     * 获取对应策略的实现类
     * @param mode  策略类型
     * @return      策略实现类名
     */
    public static String getStrategy(String mode) {
        for (UploadModeEnum item : values()) {
            if (mode.equalsIgnoreCase(item.getMode())) {
                return item.getStrategy();
            }
        }
        return "";
    }
}
