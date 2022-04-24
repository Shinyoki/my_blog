package com.senko.framework.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 * @author senko
 * @date 2022/4/24 19:28
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //链接工程
        redisTemplate.setConnectionFactory(connectionFactory);
        //key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //val
        Fastjson2JsonRedisSerializer fastjsonSerializer = new Fastjson2JsonRedisSerializer(Object.class);
        redisTemplate.setValueSerializer(fastjsonSerializer);
        redisTemplate.setHashKeySerializer(fastjsonSerializer);
        //bean后置处理
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
