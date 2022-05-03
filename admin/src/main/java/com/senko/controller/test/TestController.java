package com.senko.controller.test;

import com.senko.common.core.AjaxResult;
import com.senko.common.core.test.TestUser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 测试
 *
 * @author senko
 * @date 2022/4/29 22:37
 */
@RestController
public class TestController {

    /**
     * @RequestParam用来处理 Content-Type 为 application/x-www-form-urlencoded 编码的内容
     * ，Content-Type默认为该属性。@RequestParam也可用于其它类型的请求，例如：POST、DELETE等请求。
     * <a href="https://blog.csdn.net/weixin_38004638/article/details/99655322">可选参数</a>
     */
    @PostMapping("/test/post1")
    public TestUser post(@RequestBody TestUser user) {
        return user;
    }



    @GetMapping("/test/ping")
    public AjaxResult ping() {
        return AjaxResult.success("pong!");
    }

}


