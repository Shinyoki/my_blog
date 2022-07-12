package com.senko.common.core.dto;

import lombok.*;

/**
 * QQ用户信息dto
 * @author senko
 * @date 2022/7/12 8:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QQUserInfoDTO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * qq头像
     */
    @Getter(AccessLevel.NONE)
    private String figureurl_qq_1;

    public String getAvatar() {
        return figureurl_qq_1;
    }
}
