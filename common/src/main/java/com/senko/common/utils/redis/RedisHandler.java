package com.senko.common.utils.redis;

import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * sprng redisTemplate的再次封装
 * redis工具类
 * @author senko
 * @date 2022/4/24 21:42
 */
public class RedisHandler {
    /** 先不指定类型，否则以后的ops等操作都是默认的<Object, Object>了 */
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 存储-1 ttl 的键值对
     * @param key       缓存键
     * @param value     缓存值
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 存储ttl时间的键值对
     * @param key       缓存键
     * @param value     缓存值
     * @param ttl       存活时间
     * @param timeUnit  时间单位
     */
    public <T> void set(final String key, final T value, final Integer ttl, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    /**
     * 获取缓存对象
     * @param key       缓存键
     * @return          缓存对象
     */
    public <T> T get(final String key) {
        //重新指定泛型
        ValueOperations<String, T> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 删除该键值对
     * @param key   缓存键
     * @return      成功与否
     */
    public boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 存储集合
     * @param key           缓存键
     * @param List    缓存集合
     * @param <T>           集合元素类型
     * @return              成功插入数量
     */
    public <T> long setList(final String key, final List<T> List) {
        Long affects = redisTemplate.opsForList().rightPushAll(key, List);
        return StringUtils.isNull(affects) ? 0 : affects;
    }

    /**
     * 删除兼职键值对
     * @param List 集合
     * @return           删除成功数量
     */
    public <T> long deleteList(final List<T> List) {
        return redisTemplate.delete(List);
    }

    /**
     * 获取相应的集合
     * @param key       键
     * @return          缓存集合
     */
    public <T> List<T> getList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 指定该键值对的存活时间
     * @param key       缓存键
     * @param ttl       存活时间、单位秒
     * @return
     */
    public boolean expire(final String key, final Integer ttl) {
        return expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * 指定该键值对的存活时间
     * @param key       缓存键
     * @param ttl       存活时间
     * @param timeUnit  单位
     * @return
     */
    public boolean expire(final String key, final Integer ttl, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, ttl, timeUnit);
    }

    /**
     * 获取所有的keys
     * @return
     */
    public Set<String> getKeys() {
        return getKeys("*");
    }

    /**
     * 获取匹配pattern的所有keys
     * 如 user_*  ==> user_s1、user_s2...
     * @param pattern
     * @return
     */
    public Set<String> getKeys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    //TODO 完善更多函数
}
