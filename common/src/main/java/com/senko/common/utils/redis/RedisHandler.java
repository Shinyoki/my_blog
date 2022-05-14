package com.senko.common.utils.redis;

import com.senko.common.utils.string.StringUtils;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * sprng redisTemplate的再次封装
 *
 * redis工具类，非方法大于对象原则，需要从Spring IOC获取实例后在调用
 * @author senko
 * @date 2022/4/24 21:42
 */
@SuppressWarnings("all")
@Component
public class RedisHandler {
    /** 先不指定类型，否则以后的ops等操作都是默认的<Object, Object>了 */
    @Autowired
    private RedisTemplate redisTemplate;

    /** ===========================Key===================== */

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
    public <T> void set(final String key, final T value, int ttl, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    /**
     * 获取缓存对象
     * @param key       缓存键
     * @return          缓存对象
     */
    public Object get(final String key) {
        //重新指定泛型
        return redisTemplate.opsForValue().get(key);
    }

    /** =================Delete=============== */

    /**
     * 删除该键值对
     * @param key   缓存键
     * @return      成功与否
     */
    public Boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除一系列的键值对
     * @param keys  key集合
     * @return      成功删除个数
     */
    public Long delete(final Collection<?> keys) {
        return redisTemplate.delete(keys);
    }

    /** ====================List================== */

    /**
     * 存储集合
     * @param key           缓存键
     * @param List    缓存集合
     * @param <T>           集合元素类型
     * @return              成功插入数量
     */
    public <T> Long setList(final String key, final List<T> List) {
        Long affects = redisTemplate.opsForList().rightPushAll(key, List);
        return StringUtils.isNull(affects) ? 0 : affects;
    }

    /**
     * 删除兼职键值对
     * @param List 集合
     * @return           删除成功数量
     */
    public <T> Long deleteList(final List<T> List) {
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

    /** ===================Expire================= */

    /**
     * 指定该键值对的存活时间
     * @param key       缓存键
     * @param ttl       存活时间、单位秒
     * @return
     */
    public Boolean expire(final String key, long ttl) {
        return expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * 指定该键值对的存活时间
     * @param key       缓存键
     * @param ttl       存活时间
     * @param timeUnit  单位
     * @return
     */
    public Boolean expire(final String key, long ttl, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, ttl, timeUnit);
    }

    /**
     * 获取ttl
     * @param key   键
     * @return      剩余时间
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /** =======================Keys===================== */

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

    /**
     * 增加值
     * @param key       键
     * @param increment 增量
     * @return          值
     */
    public Long increment(final String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }


    /**
     * 自增并重新设置ttl
     * （用在限流的地方）
     * @param key   键
     * @param ttl   ttl（秒）
     * @return      自增后的值
     */
    public Long incrementAndResetExpire(final String key, long ttl) {
        Long count = increment(key, 1);
        /**
         * 在第一次增值后设置ttl，之后重复增自增也不会修改ttl
         */
        if (StringUtils.isNotNull(count) && count == 1) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        }
        return count;
    }

    /**
     * 自减
     * @param key           键
     * @param decrement     数量
     * @return              减去后的数量
     */
    public Long decrement(final String key, long decrement) {
        return redisTemplate.opsForValue().decrement(key, decrement);
    }

    /** ==================HashMap================ */

    /**
     * 设置hash键值对
     * @param key       键
     * @param hashKey   哈希键（字段）
     * @param value     存储对象
     */
    public <T> void hSet(final String key, final String hashKey, final T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 设置hash键值对
     * @param key       键
     * @param hashKey   哈希键（字段）
     * @param value     存储对象
     * @param ttl       ttl
     * @return          1：
     */
    public <T> Boolean hSet(final String key, final String hashKey, final T value, long ttl) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, ttl);
    }

    /**
     * 获取哈希键对应存储对象
     * @param key       键
     * @param hashKey   哈希键
     * @return          对象
     */
    public Object hGet(final String key, final String hashKey) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }

    /**
     * 获取该key的所有hashMap entry
     * @param key   键
     * @return      hashMap
     */
    public Map<String , Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置哈希map
     * @param key   键
     * @param map   hashMap
     */
    public <T> void hSetAll(final String key, final Map<String, T> map) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, map);
    }

    /**
     * 设置哈希map 并设定ttl
     * @param key   键
     * @param map   hashMap
     * @param ttl   ttl
     * @return
     */
    public <T> Boolean hSetAll(final String key, final Map<String, T> map, long ttl) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, map);
        return expire(key, ttl);
    }

    /**
     * 删除hash键值对
     * @param key       键
     * @param hashKeys  需要删除的hash键
     */
    public Long hDelete(final String key, Object... hashKeys) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        return ops.delete(key, hashKeys);
    }

    /**
     * 是否存在该hashKey
     * @param key       键
     * @param hashKey   hash键
     */
    public Boolean hHasKey(final String key, final String hashKey) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        return ops.hasKey(key, hashKey);
    }

    /**
     * 自增
     * @param key       键
     * @param hahsKey   哈希键
     * @param increment 自增量
     */
    public Long hIncrement(final String key, final String hahsKey, long increment) {
        return redisTemplate.opsForHash().increment(key, hahsKey, increment);
    }

    /**
     * 自减
     * @param key       键
     * @param hashKey   哈希键
     * @param decrement 自减量
     */
    public Long hDecrement(final String key, final String hashKey, long decrement) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        return ops.increment(key, hashKey, -decrement);
    }

    /**
     * =================ZSet适用于点赞排名=================
     */

    /**
     * 添加元素，并提升一定score
     * @param key       键
     * @param value     值
     * @param score     相较于上一个元素的增量
     */
    public Double zIncrement(final String key, final Object value, double score) {
        ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();
        return ops.incrementScore(key, value, score);
    }

    /**
     * 添加元素，并降级一定score
     * @param key       键
     * @param value     值
     * @param score     相较于上一个元素的排序减量
     */
    public Double zDecrement(final String key, final Object value, double score) {
        return zIncrement(key, value, -score);
    }

    /**
     * 返回 以Value为key，Score排序为值 的map
     * 以从高到低的形式
     * @param key           ZSet键
     * @param startIndex    起始地址
     * @param endIndex      终止地址
     * @return              以Value为key，Score排序为值 的map
     */
    public Map<Object , Double> zReverseRangeWithScore(final String key, long startIndex, long endIndex) {
        ZSetOperations<Object, Object> ops = redisTemplate.opsForZSet();
        // high score to low
        HashMap<Object, Double> map = new HashMap<>();
        ops.reverseRangeWithScores(key, startIndex, endIndex).stream()
                .forEach((e)->map.put(
                        e.getValue(),
                        e.getScore()
                        ));
        return map;
    }

    /**
     * 返回 以Value为key，Score排序为值 的map
     * 以从高到低的形式
     * @param key           ZSet键
     * @return              以Value为key，Score排序为值 的map
     */
    public Map<Object , Double> zAllRangeWithScore(final String key) {
        ZSetOperations<Object, Object> ops = redisTemplate.opsForZSet();
        // high score to low
        HashMap<Object, Double> map = new HashMap<>();
        ops.reverseRangeWithScores(key, 0, -1).stream()
                .forEach((e)->map.put(
                        e.getValue(),
                        e.getScore()
                ));
        return map;
    }

    /**
     * 获取集合中value所对应的score
     * @param key       键
     * @param value     值
     * @return          score
     */
    public Double zScore(final String key, final Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /** ==================Set============= */

    /**
     * 获取普通Set的所有元素
     * @param key
     * @return
     */
    public <T> Set<T> sMembers(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 向Set中添加元素
     * @param key
     * @param values
     * @return
     */
    public <T> Long sAdd(final String key, final T... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 向Set中添加元素并设置该集合的ttl
     * @param key
     * @param values
     * @param ttl
     * @return
     */
    public <T> Long sAddWithExpire(final String key, long ttl, final T... values) {
        Long count = sAdd(key, values);
        expire(key, ttl);
        return count;
    }

    /**
     * 是否为Set中的元素
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean sIsMember(final String key, final T value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public <T> Boolean sIsNotMember(final String key, final T value) {
        return !sIsMember(key, value);
    }

    /**
     * 集合大小
     * @param key
     * @return
     */
    public Long sSize(final String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 删除集合中的元素
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    public <T> Long sRemove(final String key, T... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /** ====================List=============== */
    /**
     * 取集合Range内的元素
     * @param key
     * @param startIndex
     * @param endIndex
     * @param <T>
     * @return
     */
    public <T> List<T> lRange(final String key, long startIndex, long endIndex) {
        return redisTemplate.opsForList().range(key, startIndex, endIndex);
    }

    /**
     * 集合大小
     * @param key
     * @return
     */
    public Long lSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 取集合的第index个元素
     * @param key
     * @param index
     * @param <T>
     * @return
     */
    public <T> T lIndex(final String key, long index) {
        ListOperations<String , T> listOperations = redisTemplate.opsForList();
        return listOperations.index(key, index);
    }

    /**
     * 右添加集合元素
     * @param key
     * @param value
     * @return      下标
     */
    public <T> Long lPush(final String key, final T value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 右添加集合元素 并设置集合的过期时间
     * @param key
     * @param value
     * @param ttl
     * @return      下标
     */
    public <T> Long lPush(final String key, final T value, long ttl) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, ttl);
        return index;
    }

    /**
     * 右添加所有元素
     * @param key
     * @param values
     * @return          添加成功个数
     */
    public <T> Long lPushAll(final String key, final T... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 右添加所有元素 并设置集合的ttl
     * @param key
     * @param ttl
     * @param values
     * @return          添加成功个数
     */
    public <T> Long lPushAll(final String key, long ttl, final T... values) {
        Long count =  redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, ttl);
        return count;
    }

    /**
     * 删除遇到的count个元素
     * @param key
     * @param value
     * @param count
     * @return          删除个数
     */
    public <T> Long lRemove(final String key, final T value, long count) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * ==============BitMap==============
     */

    /**
     * BitMap
     * @param key       键
     * @param offset    偏移量
     * @param value     值 true or false
     * @return
     */
    public Boolean bitAdd(final String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 获取Bitmap中offset偏移量对应的值
     * @param key
     * @param offset
     * @return
     */
    public Boolean bitGet(final String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 返回bitmap中的元素个数
     * @param key
     * @return
     */
    public Long bitCount(final String key) {
        //RedisCallback
        return (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount(key.getBytes(StandardCharsets.UTF_8));
            }
        });
    }

    /**
     * BitMap bitField
     * @param key
     * @param limit
     * @param offset
     * @return
     */
    public List<Long> bitField(final String key, int limit, long offset) {
        return (List<Long>) redisTemplate.execute((RedisCallback<List<Long>>) con ->
                con.bitField(key.getBytes(),
                        BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset)));
    }

    /**
     * bit all
     * @param key
     * @return
     */
    public byte[] bitGetAll(final String key) {
        return (byte[]) redisTemplate.execute(new RedisCallback<byte[]>(){
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes(StandardCharsets.UTF_8));
            }
        });
    }

    /** ===================HypperLongLong=========== */

    //copy
    public Long hyperAdd(String key, Object... value) {
        return redisTemplate.opsForHyperLogLog().add(key, value);
    }

    //copy
    public Long hyperGet(String... key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    //copy
    public void hyperDel(String key) {
        redisTemplate.opsForHyperLogLog().delete(key);
    }

    /** ====================GEO地理============= */
    //copy
    public Long geoAdd(String key, Double x, Double y, String name) {
        return redisTemplate.opsForGeo().add(key, new Point(x, y), name);
    }

    //copy
    public List<Point> geoGetPointList(String key, Object... place) {
        return redisTemplate.opsForGeo().position(key, place);
    }

    //copy
    public Distance geoCalculationDistance(String key, String placeOne, String placeTow) {
        return redisTemplate.opsForGeo()
                .distance(key, placeOne, placeTow, RedisGeoCommands.DistanceUnit.KILOMETERS);
    }

    //copy
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, String place, Distance distance, long limit, Sort.Direction sort) {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates();
        // 判断排序方式
        if (Sort.Direction.ASC == sort) {
            args.sortAscending();
        } else {
            args.sortDescending();
        }
        args.limit(limit);
        return redisTemplate.opsForGeo()
                .radius(key, place, distance, args);
    }

    //copy
    public List<String> geoGetHash(String key, String... place) {
        return redisTemplate.opsForGeo()
                .hash(key, place);
    }

}
