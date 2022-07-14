package com.senko.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件
 * @author senko
 * @date 2022/7/14 9:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;


}
