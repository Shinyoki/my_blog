package com.senko;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.dto.ArticlesOnOneDayDTO;
import com.senko.common.core.dto.CategoryDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.entity.MenuEntity;
import com.senko.system.mapper.ArticleMapper;
import com.senko.system.mapper.CategoryMapper;
import com.senko.system.mapper.MenuMapper;
import com.senko.system.service.IMenuService;
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

    @Autowired
    private IMenuService menuService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void testarticle() {
        List<ArticlesOnOneDayDTO> articles = articleMapper.listOfArticlesOnOneDay();
        for (ArticlesOnOneDayDTO article : articles) {
            String s = JSON.toJSONString(article);
            System.out.println(s);
        }
    }

    @Test
    void test1() {
        List<CategoryDTO> categoryDTOS = categoryMapper.listOfCategoryDTO();
        categoryDTOS.forEach(System.out::println);
    }
    @Test
    void listMenusForUser() {
        List<MenuForUserDTO> menuForUserDTOS = menuService.listMenusForUser(1);
        menuForUserDTOS.forEach(System.out::println);
    }

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
