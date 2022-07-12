package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 第三方平台的用户信息
 * @author senko
 * @date 2022/7/12 6:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialUserInfoDTO {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;
}
