package com.senko.framework.config.mysql;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
        this.strictInsertFill(metaObject, "create_time", LocalDateTime.class, LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        this.strictInsertFill(metaObject, "update_time", LocalDateTime.class, LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "update_time", LocalDateTime.class,  LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
    }
}
