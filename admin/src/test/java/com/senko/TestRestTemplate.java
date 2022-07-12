package com.senko;

import com.senko.controller.test.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试RestTemplate
 * @author senko
 * @date 2022/7/12 15:58
 */
@SpringBootTest
public class TestRestTemplate {

    @Autowired
    private TestService service;

    @Test
    void test1() {
//        service.test();
//        service.parse();
        service.getGithubUserInfo();
    }
}
