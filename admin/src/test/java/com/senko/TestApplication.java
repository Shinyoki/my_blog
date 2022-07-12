package com.senko;

import com.alibaba.fastjson.JSON;
import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.framework.properties.GithubConfigurationProperties;
import com.senko.framework.properties.QQConfigurationProperties;
import com.senko.system.mapper.RoleMapper;
import com.senko.system.service.IRoleService;
import com.senko.system.service.IUserAuthService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
*
* @author senko
* @date 2022/4/26 14:53
*/
@SpringBootTest
@SuppressWarnings("all")
public class TestApplication {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(TestApplication.class);
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

    @Autowired
    private QQConfigurationProperties qqConfigurationProperties;

    @Autowired
    private GithubConfigurationProperties githubConfigurationProperties;

    @Autowired
    private RestTemplate restTemplate;


    @Test
    void testRestTemplate() {
        String url = "https://api.minecraftservices.com/minecraft/profile";
        HttpHeaders headers = new HttpHeaders();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ4dWlkIjoiMjUzNTQ0ODQ2Nzc3NzA1OSIsImFnZyI6IkFkdWx0Iiwic3ViIjoiYzY0ZmYzZjUtZjhiYS00MmM5LTkyM2UtMTQ3MTVkODY0MDgxIiwibmJmIjoxNjU3NjExOTYxLCJhdXRoIjoiWEJPWCIsInJvbGVzIjpbXSwiaXNzIjoiYXV0aGVudGljYXRpb24iLCJleHAiOjE2NTc2OTgzNjEsImlhdCI6MTY1NzYxMTk2MSwicGxhdGZvcm0iOiJVTktOT1dOIiwieXVpZCI6IjBkZjFlZmY0NDdiNDZhMWQzMmUyYTQ2ZmUzNDhmNmJjIn0.2yMCLd_J9DAPnWbZhYX7IjkPDZ9opz98PqdfNtun-Iw";
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        String forObject = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        logger.info(forObject);
    }

    @Test
    void testGithubConfig() {
        logger.info("开始输出github配置信息");
        logger.info("githubConfigurationProperties.getClientId() = {}", githubConfigurationProperties.getClientId());
        logger.info("githubConfigurationProperties.getClientSecret() = {}", githubConfigurationProperties.getClientSecret());
        logger.info("githubConfigurationProperties.getCheckTokenUrl() = {}", githubConfigurationProperties.getCheckTokenUrl());
        logger.info("githubConfigurationProperties.getAuthentication() = {}", githubConfigurationProperties.getAuthentication());
        logger.info("githubConfigurationProperties.getUserInfoUrl() = {}", githubConfigurationProperties.getUserInfoUrl());
    }


    @Test
    void testqq() {
        logger.info("获取到的qq配置");
        logger.info(qqConfigurationProperties.getAppId());
        logger.info(qqConfigurationProperties.getCheckTokenUrl());
        logger.info(qqConfigurationProperties.getUserInfoUrl());
    }

    @Test
    void test4() {
        List<String> images = new LinkedList<>();
        images.add("https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        images.add("https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg");

        System.out.println(JSON.toJSONString(images));
    }

    @Test
    void test3() {
//        LocalDateTime localDateTime = BeanCopyUtils.copyObject(LocalDateTime.now(), LocalDateTime.class);
        LocalDateTime localDateTime = JSON.parseObject(JSON.toJSONString(LocalDateTime.now()), LocalDateTime.class);
        System.out.println("当前时间"+localDateTime);
    }

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
