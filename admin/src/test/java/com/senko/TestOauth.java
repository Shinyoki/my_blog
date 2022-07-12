package com.senko;

import com.senko.common.core.vo.GithubVO;
import com.senko.system.service.IUserAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author senko
 * @date 2022/7/12 16:41
 */
@SpringBootTest
public class TestOauth {

    @Autowired
    private IUserAuthService userAuthService;

    @Test
    void githubTest() {
        GithubVO githubVO = new GithubVO("457bb0f3548cc1375c1c");
        userAuthService.githubLogin(githubVO);
    }
}
