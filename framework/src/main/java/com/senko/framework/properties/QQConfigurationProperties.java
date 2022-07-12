package com.senko.framework.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * QQ配置属性
 * @author senko
 * @date 2022/7/12 7:51
 */
@Configuration
@ConfigurationProperties(prefix = "login.qq")
public class QQConfigurationProperties {
    /**
     * QQ appId
     */
    private String appId;
    /**
     * 校验token地址
     */
    private String checkTokenUrl;
    /**
     * QQ用户信息地址
     */
    private String userInfoUrl;

    public QQConfigurationProperties() {
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setCheckTokenUrl(String checkTokenUrl) {
        this.checkTokenUrl = checkTokenUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getCheckTokenUrl() {
        return checkTokenUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public QQConfigurationProperties(String appId, String checkTokenUrl, String userInfoUrl) {
        this.appId = appId;
        this.checkTokenUrl = checkTokenUrl;
        this.userInfoUrl = userInfoUrl;
    }
}
