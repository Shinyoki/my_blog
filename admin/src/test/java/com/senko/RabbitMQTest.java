package com.senko;

import com.senko.controller.system.SysUserAuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author senko
 * @date 2022/7/14 12:47
 */
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private SysUserAuthController userAuthController;

    @Test
    void testEmail() {
        userAuthController.sendCode("1641801056@qq.com");
    }

}
