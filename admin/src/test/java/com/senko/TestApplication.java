package com.senko;

import cn.hutool.core.util.RandomUtil;
import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.system.mapper.RoleMapper;
import com.senko.system.service.IRoleService;
import com.senko.system.service.IUserAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


/**
*
* @author senko
* @date 2022/4/26 14:53
*/
@SpringBootTest
@SuppressWarnings("all")
public class TestApplication {
    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHandler redisHandler;

    @Test
    void bitmap3() {
//        System.out.println(new String(redisHandler.bitGetAll("sun"), Charset.forName("UTF-8")));
        //fastjson无法处理
//        System.out.println(redisHandler.get("sun").getClass().getName());
    }

    @Test
    void bitMap2() {
        System.out.println(redisHandler.bitCount("sun"));
    }

    @Test
    void bitmap() {
        redisHandler.bitAdd("sun", 0, true);
        redisHandler.bitAdd("sun", 1, false);
        redisHandler.bitAdd("sun", 2, false);
        redisHandler.bitAdd("sun", 3, true);
        redisHandler.bitAdd("sun", 4, true);
    }

    @Test
    void zSet2() {
//        System.out.println(redisHandler.zDecrement("sun", 2333, 20));
        redisHandler.hSet("map2", "sun4", "dsadas");
    }

    @Test
    void hMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("s1", 1);
        map.put("s2", 2);
        map.put("s3", 3);
        redisHandler.hSetAll("map2", map);
    }

    @Test
    void testList() {
      redisHandler.hSet("map1", "sun1", 231);
      redisHandler.hSet("map1", "sun2", 232);
      redisHandler.hSet("map1", "sun3", 233);

        redisHandler.hIncrement("map1", "sun1", 1000);

        System.out.println(redisHandler.hGet("map1", "sun1"));
    }

    @Test
    void tZSET() {
        String ZSET_KEY = "sun";
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        if (zSetOperations.size(ZSET_KEY) == 0){
            for (int i = 1; i <= 10; i++) {
                zSetOperations.add(ZSET_KEY,"文章:"+i, (int)(Math.random()*10+i));
            }
        }

        //ASC根据分数从小到大排序,DESC反之
//        Set<ZSetOperations.TypedTuple<Object>> typedTuples = zSetOperations.rangeWithScores(ZSET_KEY, 0, -1);
//        for (ZSetOperations.TypedTuple<Object> typedTuple : typedTuples) {
//            System.out.println(typedTuple.getValue() + "   " + typedTuple.getScore());
//        }

        Map<Object, Double> sun = redisHandler.zReverseRangeWithScore("sun", 0, -1);
        for (Map.Entry<Object, Double> objectDoubleEntry : sun.entrySet()) {
                System.out.println(objectDoubleEntry.getKey() + "   " + objectDoubleEntry.getValue());
        }


    }

    @Test
    void tred() {
        System.out.println(redisTemplate.opsForValue().increment("sun", 1));
        System.out.println(redisTemplate.opsForValue().increment("sun", 1));
        System.out.println(redisTemplate.opsForValue().increment("sun", 1));
    }

    @Test
    void trs() {
        List<String> strings = roleMapper.listRolesByUserInfoId(1);
        strings.stream().forEach(System.out::println);
    }

    @Test
    void testService() {
        System.out.println(userAuthService.getByUsername("admin@qq.com"));
    }

    @Test
    void testRoles() {
        List<ResourceRoleDTO> resourceRoleDTOS = roleMapper.listOfNonAnonymousResourcesRoles();
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleDTOS) {
            System.out.println(resourceRoleDTO);
        }
    }
}
