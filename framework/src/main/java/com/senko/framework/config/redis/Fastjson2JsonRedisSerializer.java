package com.senko.framework.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Fastjson版redis序列化/反序列化器
 * @author senko
 * @date 2022/4/24 19:53
 */
public class Fastjson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    /** 序列化为String所使用的字符集 */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /** Target Object Clazz Type */
    private Class<T> clazz;

    static {
        /**
         * 开启Fastjson的AutoType支持
         *
         * fastjson在解析json对象时，会使用autoType实例化某一个具体的类
         * ，并调用set/get方法访问属性。漏洞出现在Fastjson autoType处理json对象时
         * ，没有对@type字段进行完整的安全性验证，我们可以传入危险的类并调用危险类连接远程RMI服务器
         * ，通过恶意类执行恶意代码，进而实现远程代码执行漏洞。
         *
         * ParserConfig.getGlobalInstance().addAccept("com.senko.common.core.entity");
         */
        ParserConfig.getGlobalInstance()
                .setAutoTypeSupport(true);
    }

    public Fastjson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * obj 2 bytes array
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null)
            return new byte[0];
        //Obj -> jsonString -> bytes array
        return JSON.toJSONString(t, SerializerFeature.WriteClassName)
                .getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0)
            return null;
        String json = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(json, clazz);
    }
}
