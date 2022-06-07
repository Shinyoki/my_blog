package com.senko;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.common.dto.ArticleViewsRankDTO;
import com.senko.common.common.dto.ArticlesOnOneDayDTO;
import com.senko.common.common.dto.CategoryDTO;
import com.senko.common.common.dto.PhotoAlbumBackDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.common.entity.ArticleEntity;
import com.senko.common.core.entity.MenuEntity;
import com.senko.common.core.entity.RoleEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.mapper.*;
import com.senko.system.service.IMenuService;
import com.senko.system.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UniqueViewMapper uniqueViewMapper;

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PhotoAlbumMapper photoAlbumMapper;

    @Test
    void testPhotoAlbum() {
        ConditionVO conditionVO = ConditionVO.builder().keywords("").build();
        List<PhotoAlbumBackDTO> photoAlbumBackDTOS = photoAlbumMapper.listPhotoAlbumDTO(0L, 10L, conditionVO);
        System.out.println(JSON.toJSONString(photoAlbumBackDTOS));
    }
    @Test
    void  testMPSave() {
        LambdaQueryWrapper<RoleEntity> query = new LambdaQueryWrapper<RoleEntity>()
                .select(RoleEntity::getRoleLabel, RoleEntity::getRoleName, RoleEntity::getCreateTime,RoleEntity::getUpdateTime,RoleEntity::getIsDisable)
                .eq(RoleEntity::getRoleName, "测试2")
                .eq(RoleEntity::getRoleLabel, "test");
        List<RoleEntity> roles = roleService.list(query);
        RoleEntity role = roles.get(0);
        System.out.println("更新前"+role);
        role.setRoleLabel("test");
        role.setRoleName("测试");
        roleService.save(role);
        System.out.println("更新后"+role);

    }

    @Test
    void testCount() {
        System.out.println(chatRecordMapper.selectCount(null));
    }

    @Test
    void testScore() {
        LinkedList<Integer> ids = new LinkedList<>(Arrays.asList(54, 55, 56));
        LinkedList<Integer> scores = new LinkedList<>(Arrays.asList(1,2,3));
        Map<Object, Double> map = new HashMap<>();

        LambdaQueryWrapper<ArticleEntity> queryWrapper = new LambdaQueryWrapper<ArticleEntity>()
                .select(ArticleEntity::getId, ArticleEntity::getArticleTitle)
                .in(ArticleEntity::getId, ids);

        List<ArticleEntity> articles = articleMapper.selectList(queryWrapper);

        map.put(articles.get(0).getId(), scores.get(0).doubleValue());
        map.put(articles.get(1).getId(), scores.get(1).doubleValue());
        map.put(articles.get(2).getId(), scores.get(2).doubleValue());

        List<ArticleViewsRankDTO> result = articles.stream()
                .map(articleEntity -> {
                            return ArticleViewsRankDTO.builder()
                                    .articleTitle(articleEntity.getArticleTitle())
                                    .viewsCount(map.get(articleEntity.getId()).intValue())
                                    .build();
                        }
                )
                .sorted(
                        Comparator.comparingInt(ArticleViewsRankDTO::getViewsCount)
                                .reversed()
                )
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    @Test
    void testUniq() {
        DateTime start = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        Date endOfToday = new Date();
        uniqueViewMapper.listOfUniqueViewDTO(start, endOfToday);
    }

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
