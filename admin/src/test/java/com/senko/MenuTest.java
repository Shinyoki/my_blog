package com.senko;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.entity.MenuEntity;
import com.senko.system.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author senko
 * @date 2022/5/3 16:41
 */
@SpringBootTest
public class MenuTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    void t3() {
        List<MenuEntity> menuEntities = menuMapper.listMenusByUserInfoId(1);
        menuEntities.stream().forEach(System.out::println);
    }

    @Test
    void queryWrapper() {
        LambdaQueryWrapper<MenuEntity> query = new LambdaQueryWrapper<MenuEntity>()
                .select(MenuEntity::getId, MenuEntity::getName, MenuEntity::getParentId, MenuEntity::getOrderNum);
        List<MenuEntity> menuEntities = menuMapper.selectList(query);
        for (MenuEntity menuEntity : menuEntities) {
            System.out.println(menuEntity);
        }
    }
}
