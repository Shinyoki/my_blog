package com.senko;

import org.junit.jupiter.api.Test;

/**
 * @author senko
 * @date 2022/7/12 19:15
 */
public class TestJSON {
    @Test
    public void test() {
        String str = "access_token=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&scope=&token_type=bearer";
        // 通过StringUtils工具类将字符串中的access_token提取出来
        String accessToken = str.substring(str.indexOf("access_token=") + "access_token=".length(), str.indexOf("&scope="));
        System.out.println(accessToken);
    }
}
