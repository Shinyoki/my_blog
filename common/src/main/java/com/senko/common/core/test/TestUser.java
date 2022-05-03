package com.senko.common.core.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 测试用
 *
 * @author senko
 * @date 2022/5/2 16:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestUser {
    private String username;
    private String password;
}
