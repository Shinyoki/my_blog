package com.senko.system.mapper;

import com.senko.common.core.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {



    /**
     * 根据用户id查询该用户有权访问的菜单
     * @param userInfoId 用户信息id
     * @return 菜单列表
     */
    List<MenuEntity> listMenusByUserInfoId(Integer userInfoId);
}
