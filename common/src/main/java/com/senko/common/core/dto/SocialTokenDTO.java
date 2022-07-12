package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 第三方登录TOKEN
 * @author senko
 * @date 2022/7/12 6:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialTokenDTO {
    /**
     * ID：用作UserAuth id
     */
    private String openId;

    /**
     * 访问TOKEN：用作UserAuth 密码
     */
    private String accessToken;

    /**
     * 登录类型
     */
    private Integer loginType;


}
