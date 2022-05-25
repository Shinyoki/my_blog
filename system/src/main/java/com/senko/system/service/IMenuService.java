package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.MenuDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.entity.MenuEntity;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;
import java.util.Map;

/**
 *  菜单服务
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IMenuService extends IService<MenuEntity> {


    /**
     * 获取登录用户的可访菜单
     * 第一层元素都是目录，children都是菜单
     * @return
     */
    List<MenuForUserDTO> listMenusForUser();

    /**
     * 获取相应User的可访菜单
     * 第一层元素都是目录，children都是菜单
     * @return              menus集合
     */
    List<MenuForUserDTO> listMenusForUser(Integer userInfoId);

    /**
     * 查询菜单后台
     * @param conditionVO   条件 菜单名
     * @return              菜单后台DTO 集合
     */
    List<MenuDTO> listMenusBack(ConditionVO conditionVO);

    /**
     * 根据菜单ID 删除其还有子菜单
     * @param menuId        菜单ID
     */
    void deleteMenuByMenuId(Integer menuId);
}

