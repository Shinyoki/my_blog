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
     * 根据前端传来的条件去筛选符合的菜单
     * @return              menus集合
     */
    List<MenuForUserDTO> listMenusForUser();
}

