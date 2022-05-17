package com.senko.framework.config.mysql;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * create_time、update_time
 * 字段属性填充器
 *
 * @author senko
 * @date 2022/4/24 22:35
 */
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class,  LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class,  LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
    }
}
