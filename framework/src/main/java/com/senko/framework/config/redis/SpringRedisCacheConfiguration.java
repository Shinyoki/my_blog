package com.senko.framework.config.redis;

import com.senko.common.utils.string.StringUtils;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Spring data cache redis config
 * Spring的缓存策略配置
 * @author senko
 * @date 2022/4/24 20:42
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)   //导入配置中的Cache信息
@EnableCaching                                          //开启缓存
public class SpringRedisCacheConfiguration {

    /**
     * Redis缓存配置
     * @param cacheProperties 被Enable的properties
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        /**
         * 绑定SpringRedis缓存的key val序列化器
         * RedisCacheConfiguration的函数只返回config对象副本，不真的修改，因此需要重新赋值
         */

        config = config.serializeKeysWith(
                //RedisSerializationContext接口的内部接口
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new StringRedisSerializer()
                )
        );
        config = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new Fastjson2JsonRedisSerializer<>(Object.class)
                )
        );

        /**
         * 根据application.yml配置所对应的CacheProperties，读取Redis的部分
         */
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (!redisProperties.isUseKeyPrefix()) {
            //不 使用前缀
            config = config.disableKeyPrefix();
        }
        if (StringUtils.isNotNull(redisProperties.getKeyPrefix())) {
            //键 前缀
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            //不 缓存null值
            config = config.disableCachingNullValues();
        }
        if (StringUtils.isNotNull(redisProperties.getTimeToLive())) {
            // ttl
            config = config.entryTtl(redisProperties.getTimeToLive());
        }

        return config;
    }
}
