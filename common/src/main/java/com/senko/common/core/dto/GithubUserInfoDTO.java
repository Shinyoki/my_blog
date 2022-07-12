package com.senko.common.core.dto;

import com.senko.common.enums.LoginTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author senko
 * @date 2022/7/12 19:55
 */
@Data
@NoArgsConstructor
public class GithubUserInfoDTO extends SocialTokenDTO {
    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户昵称
     */
    private String nickname;

    public GithubUserInfoDTO(String id, String accessToken, LoginTypeEnum loginTypeEnum, String avatarUrl, String nickname) {
        super(id, accessToken, loginTypeEnum.getType());
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
    }
}
