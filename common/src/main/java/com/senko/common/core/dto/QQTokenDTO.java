package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * qq 令牌
 * @author senko
 * @date 2022/7/12 8:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QQTokenDTO {

    /**
     * openid
     */
    private String openid;

    /**
     * 客户端id
     */
    private String client_id;

}
