package com.senko;

import com.senko.framework.config.SenkoBlogConfiguration;
import com.senko.framework.strategy.UploadStrategy;
import com.senko.framework.strategy.context.UploadStrategyContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author senko
 * @date 2022/5/16 10:54
 */
@SpringBootTest
public class UploadTest {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Test
    void t2() {
        for (Map.Entry<String, UploadStrategy> entry : uploadStrategyContext.uploadStrategyMap.entrySet()) {
            System.out.println("桶内元素");
            System.out.println(entry.getKey());
        }
    }

}
