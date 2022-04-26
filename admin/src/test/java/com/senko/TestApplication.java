package com.senko;

import com.senko.system.service.IUserAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
*
* @author senko
* @date 2022/4/26 14:53
*/
@SpringBootTest
public class TestApplication {
    @Autowired
    private IUserAuthService userAuthService;

    @Test
    void testService() {
        userAuthService.run();
    }

}
